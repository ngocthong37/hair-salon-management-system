package com.hairsalon.respository;

import com.hairsalon.entity.PaymentMethod;
import com.hairsalon.entity.User;
import com.hairsalon.model.CustomerModel;
import com.hairsalon.model.PaymentMethodModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class PaymentMethodImp implements IPaymentMethod{

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(PaymentMethodImp.class);

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public PaymentMethodModel findById(Integer id) {
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "FROM PaymentMethod as p WHERE p.id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            PaymentMethod paymentMethod = (PaymentMethod) query.getSingleResult();
            paymentMethodModel = toModel(paymentMethod);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return paymentMethodModel;
    }


    PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setId(paymentMethod.getId());

        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(paymentMethod.getCustomer().getId());
        customerModel.setCustomerName(paymentMethod.getCustomer().getCustomerName());
        customerModel.setEmail(paymentMethod.getCustomer().getEmail());
        customerModel.setPhoneNumber(paymentMethod.getCustomer().getPhoneNumber());
        paymentMethodModel.setCustomerModel(customerModel);

        paymentMethodModel.setPaymentMethodName(paymentMethod.getPaymentMethodName());
        return paymentMethodModel;
    }

}
