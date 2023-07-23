package com.hairsalon.respository;

import com.hairsalon.entity.Permission;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PermissionImp implements IPermission{

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionImp.class);

    @Override
    public List<Permission> findAll() {
        List<Permission> permissionList = new ArrayList<>();
        StringBuilder hql = new StringBuilder("FROM permission p ");
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            permissionList = query.list();
        } catch (Exception e) {
            LOGGER.error("Error has occurred in findAll Permission"+e, e);
        }
        return permissionList;
    }
}
