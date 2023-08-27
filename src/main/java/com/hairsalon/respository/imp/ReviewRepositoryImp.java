package com.hairsalon.respository.imp;

import com.hairsalon.entity.Review;
import com.hairsalon.respository.IReviewRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ReviewRepositoryImp implements IReviewRepository {

    @Autowired
    SessionFactory sessionFactory;

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(ReviewRepositoryImp.class);

    @Override
    public Integer add(Review review) {
        Session session = sessionFactory.getCurrentSession();
        try {
            Integer id = (Integer) session.save(review);
            return id;
        } catch (Exception e) {
            LOGGER.error("Error has occurred at add() ", e);
        }
        return -1;
    }
}
