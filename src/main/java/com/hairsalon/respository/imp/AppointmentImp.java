package com.hairsalon.respository.imp;

import com.hairsalon.entity.Appointment;
import com.hairsalon.model.*;
import com.hairsalon.respository.IAppointment;
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
public class AppointmentImp implements IAppointment {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<AppointmentModel> getAll() {
        Session session = sessionFactory.getCurrentSession();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();
        Set<Appointment> appointmentSet = new LinkedHashSet<Appointment>();
        String hql = "FROM Appointment";
        try {
            Query query = session.createQuery(hql);
            List<Appointment> list = query.getResultList();
            for (Appointment appointment: list) {
                appointmentSet.add(appointment);
            }
            for (Appointment appointment: appointmentSet) {
                appointmentModelList.add(toModel(appointment));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return appointmentModelList;
    }

    @Override
    public Integer insert(Appointment appointment) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Integer id = (Integer) session.save(appointment);
            return id;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at add() ", e);
        }
        return -1;
    }

    @Override
    public AppointmentModel findById(Integer id) {
        AppointmentModel appointmentModel = new AppointmentModel();
        StringBuilder hql = new StringBuilder("FROM Appointment AS a");
        hql.append(" WHERE a.id = :id");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            LOGGER.info(hql.toString());
            Appointment appointment = new Appointment();
            appointment = (Appointment) query.getSingleResult();
            appointmentModel.setId(appointment.getId());
            appointmentModel.setAppointmentTime(appointment.getAppointmentTime());
            appointmentModel.setAppointmentDate(appointment.getAppointmentDate());

            CustomerModel customerModel = new CustomerModel();
            customerModel.setCustomerName(appointment.getCustomer().getCustomerName());
            customerModel.setId(appointment.getCustomer().getId());
            customerModel.setEmail(appointment.getCustomer().getEmail());
            customerModel.setPhoneNumber(appointment.getCustomer().getPhoneNumber());
            appointmentModel.setCustomerModel(customerModel);

            AppointmentStatusModel appointmentStatusModel = new AppointmentStatusModel();
            appointmentStatusModel.setId(appointment.getAppointmentStatus().getId());
            appointmentStatusModel.setStatus(appointment.getAppointmentStatus().getStatus());
            appointmentModel.setAppointmentStatusModel(appointmentStatusModel);

            SalonModel salonModel = new SalonModel();
            salonModel.setId(appointment.getSalon().getId());
            salonModel.setSalonName(appointment.getSalon().getSalonName());
            salonModel.setAddress(appointment.getSalon().getAddress());
            salonModel.setPhoneNumber(appointment.getSalon().getPhoneNumber());
            appointmentModel.setSalonModel(salonModel);

            HairServiceModel hairServiceModel = new HairServiceModel();
            hairServiceModel.setId(appointment.getServiceHair().getId());
            hairServiceModel.setServiceName(appointment.getServiceHair().getServiceName());
            hairServiceModel.setUrl(appointment.getServiceHair().getUrl());
            hairServiceModel.setPrice(appointment.getServiceHair().getPrice());
            hairServiceModel.setDescription(appointment.getServiceHair().getDescription());
            appointmentModel.setHairServiceModel(hairServiceModel);

        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return appointmentModel;
    }

    @Override
    public Appointment findAppointmentById(Integer id) {
        StringBuilder hql = new StringBuilder("FROM Appointment AS a");
        hql.append(" WHERE a.id = :id");
        Appointment appointment = new Appointment();
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            LOGGER.info(hql.toString());
            appointment = (Appointment) query.getSingleResult();
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return appointment;
    }

    @Override
    public List<AppointmentModel> getAllByStatusId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();
        Set<Appointment> appointmentSet = new LinkedHashSet<Appointment>();
        String hql = "FROM Appointment a where a.appointmentStatus.id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Appointment> list = query.getResultList();
            for (Appointment appointment: list) {
                appointmentSet.add(appointment);
            }
            for (Appointment appointment: appointmentSet) {
                appointmentModelList.add(toModel(appointment));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll by status id " + e);
        }
        return appointmentModelList;
    }

    @Override
    public List<AppointmentModel> getAllByCustomerId(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();
        Set<Appointment> appointmentSet = new LinkedHashSet<Appointment>();
        String hql = "FROM Appointment a where a.customer.id = :id";

        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            List<Appointment> list = query.getResultList();
            for (Appointment appointment: list) {
                appointmentSet.add(appointment);
            }
            for (Appointment appointment: appointmentSet) {
                appointmentModelList.add(toModel(appointment));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll by customer id " + e);
        }
        return appointmentModelList;
    }

    @Override
    public Integer updateStatusAppointment(Appointment appointment) {
        Session session = sessionFactory.getCurrentSession();
        try {
            System.out.println("Come here");
            session.update(appointment);
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at update() ", e);
            return 0;
        }
    }

    public AppointmentModel toModel(Appointment appointment) {
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(appointment.getId());
        appointmentModel.setAppointmentTime(appointment.getAppointmentTime());
        appointmentModel.setAppointmentDate(appointment.getAppointmentDate());
        SalonModel salonModel = new SalonModel();
        salonModel.setId(appointment.getSalon().getId());
        salonModel.setSalonName(appointment.getSalon().getSalonName());
        salonModel.setPhoneNumber(appointment.getSalon().getPhoneNumber());
        salonModel.setAddress(appointment.getSalon().getAddress());
        appointmentModel.setSalonModel(salonModel);
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(appointment.getCustomer().getId());
        customerModel.setEmail(appointment.getCustomer().getEmail());
        customerModel.setPhoneNumber(appointment.getCustomer().getPhoneNumber());
        customerModel.setCustomerName(appointment.getCustomer().getCustomerName());
        appointmentModel.setCustomerModel(customerModel);
        HairServiceModel hairServiceModel = new HairServiceModel();
        hairServiceModel.setId(appointment.getServiceHair().getId());
        hairServiceModel.setServiceName(appointment.getServiceHair().getServiceName());
        hairServiceModel.setPrice(appointment.getServiceHair().getPrice());
        hairServiceModel.setDescription(appointment.getServiceHair().getDescription());
        hairServiceModel.setUrl(appointment.getServiceHair().getUrl());
        appointmentModel.setHairServiceModel(hairServiceModel);
        AppointmentStatusModel appointmentStatusModel = new AppointmentStatusModel();
        appointmentStatusModel.setId(appointment.getAppointmentStatus().getId());
        appointmentStatusModel.setStatus(appointment.getAppointmentStatus().getStatus());
        appointmentModel.setAppointmentStatusModel(appointmentStatusModel);
        return appointmentModel;
    }

}
