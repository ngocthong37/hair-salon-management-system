package com.hairsalon.respository;

import com.hairsalon.entity.Customer;
import com.hairsalon.entity.OrderProduct;
import com.hairsalon.entity.OrderStatus;
import com.hairsalon.entity.Product;
import com.hairsalon.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.criterion.Order;
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
public class ProductImp implements IProduct{
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ServiceHairImp.class);

    @Override
    public List<ProductModel> findAll() {

        List<ProductModel> productModelList = new ArrayList<>();
        Set<Product> productModelSet = new LinkedHashSet<Product>();
        String hql = "FROM Product";
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            List<Product> list = query.getResultList();
            for (Product product: list) {
                productModelSet.add(product);
            }
            for (Product product: productModelSet) {
                productModelList.add(toModel(product));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return productModelList;
    }

    @Override
    public List<ProductModel> findAllByCategoryId(Integer cateId) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductModel> productModelList = new ArrayList<>();
        Set<Product> productModelSet = new LinkedHashSet<Product>();
        String hql = "FROM Product P where P.category.id = :id";
        try {
            Query query = session.createQuery(hql);
            query.setParameter("id", cateId);
            List<Product> list = query.getResultList();
            for (Product product : list) {
                productModelSet.add(product);
            }
            for (Product product : productModelSet) {
                productModelList.add(toModel(product));
            }
        } catch (Exception e) {
            LOGGER.error("Error has occurred in findAll " + e, e);
        }
        return productModelList;
    }

    @Override
    public Product findById(Integer id) {
        StringBuilder hql = new StringBuilder("From Product as P");
        hql.append("where P.id := id");
        Product product = new Product();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            product = (Product) query.getSingleResult();
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return product;
    }

    @Override
    public Integer insert(OrderProduct orderProduct) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Integer id = (Integer) session.save(orderProduct);
            return id;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at add() ", e);
        }
        return -1;
    }

    @Override
    public OrderProductModel findOrderById(Integer id) {
        StringBuilder hql = new StringBuilder("From OrderProduct as OP");
        hql.append(" where OP.id = :id");
        OrderProduct orderProduct = new OrderProduct();
        OrderProductModel orderProductModel = new OrderProductModel();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            orderProduct = (OrderProduct) query.getSingleResult();
            orderProductModel = toOrderProductModel(orderProduct);
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return orderProductModel;
    }

    ProductModel toModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setProductName(product.getName());
        productModel.setImageUrl(product.getImageUrl());
        return productModel;
    }

    OrderProductModel toOrderProductModel(OrderProduct orderProduct) {
        OrderProductModel orderProductModel = new OrderProductModel();
        orderProductModel.setId(orderProduct.getId());


        ProductItemModel productItemModel = new ProductItemModel();
        productItemModel.setId(orderProduct.getProductItem().getId());
        productItemModel.setProductName(orderProduct.getProductItem().getProductName());
        orderProductModel.setProductItemModel(productItemModel);

        CustomerModel customerModel = new CustomerModel();
        customerModel.setCustomerName(orderProduct.getCustomer().getCustomerName());
        customerModel.setId(orderProduct.getCustomer().getId());

        orderProductModel.setQuantity(orderProduct.getQuantity());
        orderProductModel.setCustomerModel(customerModel);

        OrderStatusModel orderStatusModel = new OrderStatusModel();
        orderStatusModel.setId(orderProduct.getOrderStatus().getId());


        orderProductModel.setOrderStatusModel(orderStatusModel);
        return orderProductModel;
    }




}
