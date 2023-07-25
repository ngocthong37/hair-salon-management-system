package com.hairsalon.respository.imp;

import com.hairsalon.entity.Product;
import com.hairsalon.respository.IRevenue;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Repository
@Transactional
public class RevenueImp implements IRevenue {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Override
    public Double getRevenueFromService() {
        Double res = 0.0;
        StringBuilder hql = new StringBuilder("Select SUM(SH.price) from Appointment A INNER" +
                " JOIN ServiceHair SH ON A.serviceHair.id = SH.id AND A.appointmentStatus.id = 3");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            res = (Double) query.getSingleResult();
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl getRevenueFromService API: "+e,e);
        }
        return res;
    }

    @Override
    public Double getRevenueFromProduct() {
        Double res = 0.0;
        StringBuilder hql = new StringBuilder("Select SUM(OT.price) from Order O INNER" +
                " JOIN OrderItem OT ON O.id = OT.order.id AND O.orderStatus.id = 3");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            res = (Double) query.getSingleResult();
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl getRevenueFromProduct: "+e,e);
        }
        return res;
    }
}
