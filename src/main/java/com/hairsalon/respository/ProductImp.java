package com.hairsalon.respository;

import com.hairsalon.entity.Order;
import com.hairsalon.entity.OrderItem;
import com.hairsalon.entity.Product;
import com.hairsalon.entity.ProductItem;
import com.hairsalon.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class ProductImp implements IProduct{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Override
    public List<ProductModel> findAll() {

        List<ProductModel> productModelList = new ArrayList<>();
        Set<Product> productModelSet = new LinkedHashSet<Product>();
        String hql = "FROM Product";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            List<Product> list = query.getResultList();
            for (Product product: list) {
                productModelSet.add(product);
            }
            for (Product product: productModelSet) {
                productModelList.add(toModel(product));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return productModelList;
    }

    @Override
    public List<ProductModel> findAllByCategoryId(Integer cateId) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductModel> productModelList = new ArrayList<>();
        Set<Product> productModelSet = new LinkedHashSet<Product>();
        String hql = "FROM Product P where P.category.id = :id";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", cateId);
            List<Product> list = query.getResultList();
            for (Product product : list) {
                productModelSet.add(product);
            }
            for (Product product : productModelSet) {
                productModelList.add(toModel(product));
            }
        } catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return productModelList;
    }

    @Override
    public Product findById(Integer id) {
        StringBuilder hql = new StringBuilder("From Product as P");
        hql.append("where P.id := id");
        Product product = new Product();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            product = (Product) query.getSingleResult();
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return product;
    }
    @Transactional
    @Override
    public Integer insert(Order order, List<OrderItem> orderItems) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Integer orderId = (Integer) session.save(order);
            for (OrderItem orderItem : orderItems) {
                orderItem.setOrder(order);
                session.save(orderItem);
            }
            return orderId;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at insert()", e);
            return -1;
        }
    }

    @Override
    public OrderModel findOrderById(Integer id) {
        StringBuilder hql = new StringBuilder("From Order as OP");
        hql.append(" where OP.id = :id");
        Order order = new Order();
        OrderModel orderModel = new OrderModel();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            order = (Order) query.getSingleResult();
            orderModel = toOrderProductModel(order);
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return orderModel;
    }

    ProductModel toModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setProductName(product.getName());
        productModel.setImageUrl(product.getImageUrl());
        return productModel;
    }

    OrderModel toOrderProductModel(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());

        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setId(order.getPaymentMethod().getId());
        paymentMethodModel.setPaymentMethodName(order.getPaymentMethod().getPaymentMethodName());
        orderModel.setPaymentMethodModel(paymentMethodModel);

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerName(order.getCustomer().getCustomerName());
        customerModel.setId(order.getCustomer().getId());
        orderModel.setCustomerModel(customerModel);

        OrderStatusModel orderStatusModel = new OrderStatusModel();
        orderStatusModel.setId(order.getOrderStatus().getId());
        orderModel.setOrderStatusModel(orderStatusModel);

        orderModel.setOrderDate(order.getOrderDate());
        orderModel.setTotalPrice(order.getTotalPrice());

        return orderModel;
    }




}
