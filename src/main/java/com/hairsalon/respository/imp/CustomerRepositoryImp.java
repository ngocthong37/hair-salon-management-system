package com.hairsalon.respository.imp;

import com.hairsalon.entity.Customer;
import com.hairsalon.entity.Order;
import com.hairsalon.entity.OrderItem;
import com.hairsalon.model.CustomerModel;
import com.hairsalon.respository.ICustomerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class CustomerRepositoryImp implements ICustomerRepository {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairRepositoryImp.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<CustomerModel> findAll() {
        Session session = sessionFactory.openSession();
        List<CustomerModel> customerModelList = new ArrayList<>();
        Set<Customer> customerModelSet = new LinkedHashSet<Customer>();
        String hql = "FROM Customer";
        try {
            Query query = session.createQuery(hql);
            List<Customer> list = query.list();
            for (Customer customer: list) {
                customerModelSet.add(customer);
            }
            for (Customer customer: customerModelSet) {
                customerModelList.add(toModel(customer));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return customerModelList;
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
    public CustomerModel findById(Integer id) {
        CustomerModel customerModel = new CustomerModel();
        StringBuilder hql = new StringBuilder("FROM Customer AS c");
        hql.append(" WHERE c.id = :id");
        try {
            Session session = sessionFactory.openSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            Customer customer = new Customer();
            customer = (Customer) query.uniqueResult();
            customerModel.setId(customer.getId());
            customerModel.setCustomerName(customer.getCustomerName());
            customerModel.setEmail(customer.getEmail());
            customerModel.setPhoneNumber(customer.getPhoneNumber());
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return customerModel;
    }

    CustomerModel toModel(Customer customer) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(customer.getId());
        customerModel.setCustomerName(customer.getCustomerName());
        customerModel.setEmail(customer.getEmail());
        customerModel.setPhoneNumber(customer.getPhoneNumber());
        return customerModel;
    }


}
