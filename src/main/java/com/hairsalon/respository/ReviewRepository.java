package com.hairsalon.respository;

import com.hairsalon.entity.ReviewServiceHair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewServiceHair, Integer> {
}
