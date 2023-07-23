package com.hairsalon.respository;

import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.model.AppointmentStatusModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;

@Transactional
@Repository
public class AppointmentStatusImp implements IAppointmentStatus{

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Autowired
    SessionFactory sessionFactory;

    public AppointmentStatusModel findById(Integer id) {
        AppointmentStatusModel appointmentStatusModel = new AppointmentStatusModel();
        StringBuilder hql = new StringBuilder("FROM AppointmentStatus AS a");
        hql.append(" WHERE a.id = :id");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            LOGGER.info(hql.toString());
            AppointmentStatus appointmentStatus = new AppointmentStatus();
            appointmentStatus = (AppointmentStatus) query.getSingleResult();
            appointmentStatusModel.setId(appointmentStatus.getId());
            appointmentStatusModel.setStatus(appointmentStatus.getStatus());
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }

        return appointmentStatusModel;
    }
}
