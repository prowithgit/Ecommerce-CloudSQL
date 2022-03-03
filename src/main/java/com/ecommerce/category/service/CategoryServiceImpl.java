package com.ecommerce.category.service;

import com.ecommerce.category.model.Category;
import com.ecommerce.category.repository.CategoryRepository;

import com.ecommerce.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Category addCategory(Category category)
    {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Integer id) throws InvalidInputException
    {
        log.info("In getCategoryById");
        List<Integer> listIds=categoryRepository.findCatId();
        System.out.println(listIds);
            if (listIds.contains(id))
            {
                System.out.println(listIds);
                Optional<Category> category=categoryRepository.findById(id);
                Category category1=category.get();
                return category1;
            }
            else
            {
                throw new InvalidInputException("Category with id:" + id + " not available.\n" +
                        "Enter valid id.");
            }
    }

    @Override
    public Category getCategoryByName(String name) throws InvalidInputException {
        List<String> list=categoryRepository.findCatName();
        boolean status=false;
        try
        {
            for(String cname:list)
            {
                if (cname.equalsIgnoreCase(name))
                {
                    status=true;
                    return categoryRepository.findByCatName(name);
                }

            }
            if(status==false)
                throw new InvalidInputException("Category with name:"+name+" not available");
        }
        catch (InvalidInputException exception)
        {
            throw new InvalidInputException(exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategoryById(Integer id, Category category)
    {
        Optional<Category> category1=categoryRepository.findById(id);
        Category category2=category1.get();
        category2.setAlias(category.getAlias());
        category2.setCatName(category.getCatName());
        category2.setEnabled(category.getEnabled());
        category2.setImageUrl(category.getImageUrl());
        category2.setParentId(category.getParentId());
        return categoryRepository.save(category2);
    }

    @Override
    public List<Category> sortCategoryByName()
    {
        List<Category> list=categoryRepository.findAll();
        Collections.sort(list);
        System.out.println(list);
        return list;
    }

    @Override
    public String deleteCatById(Integer id)
    {
        categoryRepository.deleteById(id);
        return "Category deleted";
    }
}
