package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.imp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CustomerService {
    @Autowired
    CustomerImp customerImp;

    @Autowired
    OrderStatusImp orderStatusImp;

    @Autowired
    ProductItemImp productItemImp;

    @Autowired
    EmailSendService emailSendService;

    @Autowired
    PaymentMethodImp paymentMethodImp;

    @Autowired
    OrderImp orderImp;

    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<CustomerModel> customerList = new ArrayList<>();
        customerList = customerImp.findAll();
        results.put("customerList", customerList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> order(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = new Order();
        OrderModel orderModel = new OrderModel();
        try {
            if (json == null || json.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("ERROR", "Empty JSON", ""));
            }
            JsonNode jsonObjectAppointment = objectMapper.readTree(json);
            Integer customerId = jsonObjectAppointment.get("customerId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("customerId").asText()) : 1;
            Integer payId = jsonObjectAppointment.get("payId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("payId").asText()) : 1;
            Integer totalPrice = jsonObjectAppointment.get("totalPrice") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("totalPrice").asText()) : 1;
            Integer statusId = jsonObjectAppointment.get("status") != null ?
                    jsonObjectAppointment.get("status").asInt() : 1;
            String orderDate = jsonObjectAppointment.get("orderDate") != null ?
                    jsonObjectAppointment.get("orderDate").asText() : "";

            JsonNode orderItemList = jsonObjectAppointment.get("orderItemList");


            CustomerModel customerModel = customerImp.findById(customerId);
            Customer customer = new Customer();
            customer.setId(customerModel.getId());
            customer.setCustomerName(customerModel.getCustomerName());
            customer.setEmail(customerModel.getEmail());


            OrderStatusModel orderStatusModel = orderStatusImp.findById(statusId);
            orderStatusModel = orderStatusImp.findById(statusId);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(orderStatusModel.getId());
            orderStatus.setStatus(orderStatusModel.getOrderStatus());

            PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
            paymentMethodModel = paymentMethodImp.findById(payId);
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(paymentMethodModel.getId());
            paymentMethod.setPaymentMethodName(paymentMethodModel.getPaymentMethodName());

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(orderDate, dateFormatter);

            order.setCustomer(customer);
            order.setPaymentMethod(paymentMethod);
            order.setTotalPrice(totalPrice);
            order.setOrderStatus(orderStatus);
            order.setOrderDate(parsedDate);

            String[] cc = {"n20dccn152@student.ptithcm.edu.vn"};
            List<OrderItem> orderItems = new ArrayList<>();
            if (orderItemList.isArray()) {
                for (JsonNode orderItemJson : orderItemList) {
                    OrderItem orderItem = new OrderItem();
                    ProductItemModel productItemModel = productItemImp.findById(orderItemJson.get("productItemId").asInt());
                    ProductItem productItem = new ProductItem();
                    productItem.setProductName(productItemModel.getProductName());
                    productItem.setId(productItemModel.getId());
                    productItem.setPrice(productItemModel.getPrice());
                    productItem.setStatus(productItemModel.getStatus());
                    productItem.setQuantityInStock(productItemModel.getQuantityInStock());
                    orderItem.setProductItem(productItem);
                    orderItem.setPrice(orderItemJson.get("price").asInt());
                    orderItem.setQuantity(orderItemJson.get("quantity").asInt());
                    orderItems.add(orderItem);
                }
            }
            Integer messageId = customerImp.insert(order, orderItems);
            orderModel = orderImp.findOrderById(messageId);
            if (messageId != 0) {
/*                emailSendService.sendMail(customer.getEmail(), cc, "Xác nhận đặt hàng thành công.", "Cảm ơn bé: " + customer.getCustomerName()  + " đã đặt sản phẩm " + productItem.getProductName() + ". " +
                        " Đơn hàng đang được đóng gói và sẽ chuyển tới bạn sớm nhất.");*/
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Successfully", orderModel));
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Can not make an appointment", ""));
            }


        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "An error occurred", e.getMessage()));
        }
    }


}
