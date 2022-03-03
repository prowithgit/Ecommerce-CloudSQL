package com.ecommerce.category.service;

import com.ecommerce.category.model.Category;
import com.ecommerce.exception.InvalidInputException;
import org.springframework.http.ResponseEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

public interface CategoryService
{
    Category addCategory(Category category);
    Category getCategoryById(Integer id) throws InvalidInputException;
    Category getCategoryByName(String name) throws InvalidInputException;
    List<Category> getAllCategories();
    Category updateCategoryById(Integer id,Category category);
    List<Category> sortCategoryByName();
    String deleteCatById(Integer id);
}
