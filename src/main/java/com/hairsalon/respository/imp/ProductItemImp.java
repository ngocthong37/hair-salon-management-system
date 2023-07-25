package com.hairsalon.respository.imp;

import com.hairsalon.entity.Product;
import com.hairsalon.entity.ProductItem;
import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;
import com.hairsalon.model.ProductItemModel;
import com.hairsalon.model.ProductModel;
import com.hairsalon.respository.IProductItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ProductItemImp implements IProductItem {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ProductItemImp.class);

    @Override
    public ProductItemModel findById(Integer id) {
        StringBuilder hql = new StringBuilder("From ProductItem as P where P.id = :id");
        ProductItemModel productItemModel = new ProductItemModel();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            query.setParameter("id",id);
            ProductItem productItem = new ProductItem();
            productItem = (ProductItem) query.getSingleResult();
            productItemModel = toModel(productItem);
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return productItemModel;
    }

    @Override
    public List<ProductItemModel> findByProductItemName(String productItemName) {
        List<ProductItemModel> productItemModelList = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        StringBuilder hql = new StringBuilder("FROM ProductItem AS PI");
        hql.append(" WHERE PI.productName LIKE CONCAT('%', :productItemName, '%')");
        try {
            Session session = sessionFactory.getCurrentSession();
            org.hibernate.query.Query query = session.createQuery(hql.toString());
            query.setParameter("productItemName",productItemName);
            productItemList = query.getResultList();
            for(ProductItem productItem: productItemList) {
                productItemModelList.add(toModel(productItem));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return productItemModelList;
    }

    @Override
    public Integer add(ProductItem productItem) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.save(productItem);
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at update() ", e);
            return 0;
        }
    }

    @Override
    public Integer update(ProductItem productItem) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.update(productItem);
            return 1;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at update() ", e);
            return 0;
        }
    }

    @Override
    public List<ProductItemModel> findAll() {
        StringBuilder hql = new StringBuilder("From ProductItem");
        List<ProductItemModel> productItemModelList = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql.toString());
            productItemList =  query.getResultList();
            for (ProductItem productItem: productItemList) {
                productItemModelList.add(toModel(productItem));
            }
        }
        catch (Exception e) {
            LOGGER.error("Error has occurred in Impl findById API: "+e,e);
        }
        return productItemModelList;
    }

    ProductItemModel toModel(ProductItem productItem) {
        ProductItemModel productItemModel = new ProductItemModel();
        productItemModel.setId(productItem.getId());
        productItemModel.setProductName(productItem.getProductName());
        productItemModel.setPrice(productItem.getPrice());
        productItemModel.setStatus(productItem.getStatus());
        productItemModel.setWarrantyTime(productItem.getWarrantyTime());
        productItemModel.setQuantityInStock(productItem.getQuantityInStock());

        ProductModel productModel = new ProductModel();
        productModel.setId(productItem.getProduct().getId());
        productModel.setProductName(productItem.getProduct().getName());
        productModel.setDescription(productItem.getProduct().getDescription());
        productModel.setImageUrl(productItem.getProduct().getImageUrl());
        productItemModel.setProductModel(productModel);

        return productItemModel;
    }

}
