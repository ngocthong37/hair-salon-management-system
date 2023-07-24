package com.hairsalon.respository.imp;

import com.hairsalon.entity.Order;
import com.hairsalon.model.CustomerModel;
import com.hairsalon.model.OrderModel;
import com.hairsalon.model.OrderStatusModel;
import com.hairsalon.model.PaymentMethodModel;
import com.hairsalon.respository.IOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional

public class OrderImp implements IOrder {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OrderImp.class);


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
            orderModel = toModel(order);
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return orderModel;
    }

    @Override
    public List<OrderModel> findAll() {
        StringBuilder hql = new StringBuilder("From Order as O");
        List<Order> orderList;
        List<OrderModel> orderModelList = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            orderList = query.getResultList();
            for (Order order: orderList) {
                orderModelList.add(toModel(order));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl find All API: "+e,e);
        }
        return orderModelList;
    }

    @Override
    public List<OrderModel> findAllByStatusId(Integer id) {
        StringBuilder hql = new StringBuilder("From Order as O where O.orderStatus.id = :id");
        List<Order> orderList;
        List<OrderModel> orderModelList = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id", id);
            orderList = query.getResultList();
            for (Order order: orderList) {
                orderModelList.add(toModel(order));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl find All API: "+e,e);
        }
        return orderModelList;
    }

    OrderModel toModel(Order order) {
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
