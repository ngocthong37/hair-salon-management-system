package com.hairsalon.respository.imp;

import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;
import com.hairsalon.respository.IServiceHair;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Transactional
@Repository

public class ServiceHairImp implements IServiceHair {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<HairServiceModel> getAllService() {
        Session session = sessionFactory.getCurrentSession();
        List<HairServiceModel> hairServiceModelList = new ArrayList<>();
        Set<ServiceHair> hairServiceModelSet = new LinkedHashSet<ServiceHair>();
        String hql = "FROM ServiceHair"; // Đổi 'service' thành 'Service'
        try {
            Query query = session.createQuery(hql); // Thêm kiểu dữ liệu cho Query
            List<ServiceHair> list = query.list();
            for (ServiceHair serviceHair: list) {
                hairServiceModelSet.add(serviceHair);
            }
            for (ServiceHair serviceHair: hairServiceModelSet) {
                hairServiceModelList.add(toModel(serviceHair));
            }

        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }


        return hairServiceModelList;
    }

    public HairServiceModel findById(Integer id) {
        HairServiceModel hairServiceModel = new HairServiceModel();
        StringBuilder hql = new StringBuilder("FROM ServiceHair AS s");
        hql.append(" WHERE s.id = :id");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            LOGGER.info(hql.toString());
            ServiceHair serviceHair = new ServiceHair();
            serviceHair = (ServiceHair) query.uniqueResult();
            hairServiceModel.setId(serviceHair.getId());
            hairServiceModel.setServiceName(serviceHair.getServiceName());
            hairServiceModel.setUrl(serviceHair.getUrl());
            hairServiceModel.setPrice(serviceHair.getPrice());
            hairServiceModel.setDescription(serviceHair.getDescription());
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return hairServiceModel;
    }

    @Override
    public Integer add(ServiceHair serviceHair) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(serviceHair);
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at update() ", e);
            return 0;
        }
    }

    @Override
    public Integer update(ServiceHair serviceHair) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.update(serviceHair);
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at update() ", e);
            return 0;
        }
    }


    public HairServiceModel toModel(ServiceHair serviceHair) {
        HairServiceModel hairServiceModel = new HairServiceModel();
        hairServiceModel.setId(serviceHair.getId());
        hairServiceModel.setPrice(serviceHair.getPrice());
        hairServiceModel.setServiceName(serviceHair.getServiceName());
        hairServiceModel.setDescription(serviceHair.getDescription());
        hairServiceModel.setUrl(serviceHair.getUrl());
        return hairServiceModel;
    }

}
