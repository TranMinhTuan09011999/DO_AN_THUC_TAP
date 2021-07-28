package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findAllByOrderByCategoryIdDesc();
    Category findCategoryByCategoryId(String categoryId);
}
