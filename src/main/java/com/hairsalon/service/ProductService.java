package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.CustomerImp;
import com.hairsalon.respository.OrderStatusImp;
import com.hairsalon.respository.ProductImp;
import com.hairsalon.respository.ProductItemImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Transactional
@Service
public class ProductService {
    @Autowired
    ProductImp productImp;

    @Autowired
    CustomerImp customerImp;

    @Autowired
    OrderStatusImp orderStatusImp;

    @Autowired
    ProductItemImp productItemImp;

    @Autowired
    EmailSendService emailSendService;


    public ResponseEntity<ResponseObject> findAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<ProductModel> productList = new ArrayList<>();
        productList = productImp.findAll();
        results.put("productList", productList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findAllByCateId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<ProductModel> productList = new ArrayList<>();
        productList = productImp.findAllByCategoryId(id);
        results.put("productList", productList);
        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> orderProduct(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        OrderProduct orderProduct = new OrderProduct();
        OrderProductModel orderProductModel = new OrderProductModel();
        try {
            if (json == null || json.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("ERROR", "Empty JSON", ""));
            }
            JsonNode jsonObjectAppointment = objectMapper.readTree(json);
            Integer customerId = jsonObjectAppointment.get("customerId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("customerId").asText()) : 1;
            Integer productItemId = jsonObjectAppointment.get("productItemId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("productItemId").asText()) : 1;
            Integer quantity = jsonObjectAppointment.get("quantity") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("quantity").asText()) : 1;
            Integer statusId = jsonObjectAppointment.get("status") != null ?
                    jsonObjectAppointment.get("status").asInt() : 1;

            CustomerModel customerModel = customerImp.findById(customerId);
            Customer customer = new Customer();
            customer.setId(customerModel.getId());
            customer.setCustomerName(customerModel.getCustomerName());
            customer.setEmail(customerModel.getEmail());

            ProductItemModel productItemModel = productItemImp.findById(productItemId);
            ProductItem productItem = new ProductItem();
            productItem.setProductName(productItemModel.getProductName());
            productItem.setId(productItemModel.getId());
            productItem.setPrice(productItemModel.getPrice());
            productItem.setStatus(productItemModel.getStatus());
            productItem.setQuantityInStock(productItemModel.getQuantityInStock());

            OrderStatusModel orderStatusModel = orderStatusImp.findById(statusId);
            orderStatusModel = orderStatusImp.findById(statusId);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setId(orderStatusModel.getId());
            orderStatus.setStatus(orderStatusModel.getOrderStatus());

            orderProduct.setCustomer(customer);
            orderProduct.setProductItem(productItem);
            orderProduct.setQuantity(quantity);
            orderProduct.setOrderStatus(orderStatus);

            String[] cc = {"n20dccn152@student.ptithcm.edu.vn"};

            Integer messageId = productImp.insert(orderProduct);
            orderProductModel = productImp.findOrderById(messageId);
            if (messageId != 0) {
                emailSendService.sendMail(customer.getEmail(), cc, "Xác nhận đặt hàng thành công.", "Cảm ơn bé: " + customer.getCustomerName()  + " đã đặt sản phẩm " + productItem.getProductName() + ". " +
                        " Đơn hàng đang được đóng gói và sẽ chuyển tới bạn sớm nhất.");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Successfully", orderProductModel));
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
