package com.hairsalon.respository;

import com.hairsalon.entity.User;
import com.hairsalon.model.RoleDetailModel;
import com.hairsalon.model.UserModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class UserImp implements IUser {

    private final Logger LOGGER = LoggerFactory.getLogger(UserImp.class);

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    RoleImp roleImp;

    @Override
    public List<UserModel> findAll() {
        Session session = sessionFactory.openSession();
        List<UserModel> userModelList = new ArrayList<>();
        Set<User> userModelSet = new LinkedHashSet<User>();
        String hql = "FROM User";
        try {
            Query query = session.createQuery(hql);
            List<User> list = query.list();
            for (User user: list) {
                userModelSet.add(user);
            }
            for (User user: userModelSet) {
                userModelList.add(toModel(user));
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return userModelList;
    }

    @Override
    public User add(User user) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Integer id = (Integer) session.save(user);
            if (id != null) {
                user.setId(id);
                return user;
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User findById(Integer id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM User as u WHERE u.userName = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            User user = (User) query.getSingleResult();
            if(null != user) {
                return user;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public UserModel findByUserName(String userName) {
        UserModel userModel = new UserModel();
        try {
            Session session = sessionFactory.openSession();
            StringBuilder hql = new StringBuilder();
            hql.append("FROM User as u where u.userName = :userName");
            Query query = session.createQuery(hql.toString());
            query.setParameter("userName", userName);
            User user = (User) query.getSingleResult();
            if (user != null) {
                userModel = toModel(user);
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return userModel;
    }

    @Override
    public User loadUserByUserName(String userName) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM User as u WHERE u.userName = " + userName.trim();
            Query query = session.createQuery(hql);
            User user = (User) query.getSingleResult();
            if(null != user) {
                return user;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean checkExistingUserByUsername(String username) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM User as u WHERE u.username = :username";
            Query query = session.createQuery(hql);
            query.setParameter("username", username.trim());
            List<User> users = (List<User>) query.getResultList();
            if(!users.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return false;
    }

    UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        RoleDetailModel roleDetailModel = new RoleDetailModel();
        roleDetailModel = roleImp.findRoleDetailsByRoleId(user.getRole().getRoleId());
        userModel.setRoleDetailModel(roleDetailModel);
        userModel.setUserName(user.getUserName());
        return userModel;
    }


}
