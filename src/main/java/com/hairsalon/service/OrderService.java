package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @Autowired
    ProductItemRepository productItemRepository;

    @Autowired
    EmailSendService emailSendService;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    CustomerRepository customerRepository;


    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<Order> orderModelList = new ArrayList<>();
        orderModelList = orderRepository.findAll();
        results.put("orderList", orderModelList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findAllByStatusId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<Order> orderModelList = new ArrayList<>();
        orderModelList = orderRepository.findAllById(Collections.singleton(id));
        results.put("orderList", orderModelList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findAllByCustomerId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<Order> orderModelList = new ArrayList<>();
        orderModelList = orderRepository.findAllById(Collections.singleton(id));
        results.put("orderList", orderModelList);
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


            Optional<Customer> customerModel = customerRepository.findById(customerId);
            Customer customer = new Customer();
            customer.setId(customerModel.get().getId());
            customer.setCustomerName(customerModel.get().getCustomerName());
            customer.setEmail(customerModel.get().getEmail());


            Optional<OrderStatus> orderStatusModel = orderStatusRepository.findById(statusId);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(orderStatusModel.get().getId());
            orderStatus.setStatus(orderStatusModel.get().getStatus());

            Optional<PaymentMethod> paymentMethodModel = paymentMethodRepository.findById(payId);
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setId(paymentMethodModel.get().getId());
            paymentMethod.setPaymentMethodName(paymentMethodModel.get().getPaymentMethodName());

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
                    int id = orderItemJson.get("productItemId").asInt();
                    Optional<ProductItem> productItemModel = productItemRepository.findById(orderItemJson.get("productItemId").asInt());
                    ProductItem productItem = new ProductItem();
                    productItem.setProductName(productItemModel.get().getProductName());
                    productItem.setId(productItemModel.get().getId());
                    productItem.setPrice(productItemModel.get().getPrice());
                    productItem.setStatus(productItemModel.get().getStatus());
                    productItem.setQuantityInStock(productItemModel.get().getQuantityInStock());
                    orderItem.setProductItem(productItem);
                    orderItem.setPrice(orderItemJson.get("price").asDouble());
                    orderItem.setQuantity(orderItemJson.get("quantity").asInt());
                    orderItems.add(orderItem);
                }
            }

            orderRepository.save(order);
            Order savedOrder = orderRepository.save(order);

            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(savedOrder);
                orderItemRepository.save(orderItem);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("OK", "Successfully", orderModel));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "An error occurred", e.getMessage()));
        }
    }

    public ResponseEntity<Object> updateStatusOrder(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        Integer statusCode;
        Integer orderId;
//        try {
//            jsonNode = jsonMapper.readTree(json);
//            orderId = jsonNode.get("orderId") != null ? jsonNode.get("orderId").asInt() : null;
//            statusCode = jsonNode.get("statusCode") != null ? jsonNode.get("statusCode").asInt() : -1;
//            Order order = new Order();
//            order = orderImp.findOrderById(orderId);
//            OrderStatus orderStatus = new OrderStatus();
//            orderStatus.setId(statusCode);
//            order.setOrderStatus(orderStatus);
//            Integer messageId = orderImp.updateStatusOrder(order);
//            if (orderImp.updateStatusOrder(order) < 0) {
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(new ResponseObject("ERROR", "Have error when update status code order", ""));
//            }
//        }
//        catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
//        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }


}
