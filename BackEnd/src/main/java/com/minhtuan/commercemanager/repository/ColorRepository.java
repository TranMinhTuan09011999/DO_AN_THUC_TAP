package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Color;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findAllByOrderByColorId();
    Color findColorByColorId(Integer colorId);
}
