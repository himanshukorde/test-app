package com.CarHub.repository.imageRepository;

import com.CarHub.entity.car.CarImages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImagesRepository extends JpaRepository<CarImages, Long> {
}