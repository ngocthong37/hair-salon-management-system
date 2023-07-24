package com.hairsalon.respository.imp;

import com.hairsalon.entity.OrderStatus;
import com.hairsalon.model.OrderStatusModel;
import com.hairsalon.respository.IOrderStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class OrderStatusImp implements IOrderStatus {

    @Autowired
    SessionFactory sessionFactory;
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(OrderStatusImp.class);

    @Override
    public OrderStatusModel findById(Integer id) {
        OrderStatusModel orderStatusModel = new OrderStatusModel();
        StringBuilder hql = new StringBuilder("From OrderStatus as OS");
        hql.append(" where OS.id = :id");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            OrderStatus orderStatus = new OrderStatus();
            orderStatus = (OrderStatus) query.getSingleResult();
            orderStatusModel.setOrderStatus(orderStatus.getStatus());
            orderStatusModel.setId(orderStatus.getId());
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return orderStatusModel;
    }
}
