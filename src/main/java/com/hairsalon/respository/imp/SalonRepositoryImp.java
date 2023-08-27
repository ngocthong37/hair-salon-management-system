package com.hairsalon.respository.imp;

import com.hairsalon.entity.Salon;
import com.hairsalon.model.SalonModel;
import com.hairsalon.respository.ISalonRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SalonRepositoryImp implements ISalonRepository {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairRepositoryImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<SalonModel> findAll() {
        return null;
    }

    @Override
    public SalonModel findById(Integer id) {
        SalonModel salonModel = new SalonModel();
        StringBuilder hql = new StringBuilder("FROM Salon AS s");
        hql.append(" WHERE s.id = :id");
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            LOGGER.info(hql.toString());
            Salon salon = new Salon();
            salon = (Salon) query.uniqueResult();
            salonModel.setId(salon.getId());
            salonModel.setSalonName(salon.getSalonName());
            salonModel.setAddress(salon.getAddress());
            salonModel.setPhoneNumber(salon.getPhoneNumber());
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return salonModel;
    }
}
