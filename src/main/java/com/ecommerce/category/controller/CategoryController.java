package com.ecommerce.category.controller;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.category.model.Category;
import com.ecommerce.category.service.CategoryService;
import com.ecommerce.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class CategoryController
{
    @Autowired
    @Qualifier("categoryServiceImpl")
    private CategoryService categoryService;

    @PostMapping("/addcat")
    public Category createNewCategory(@RequestBody Category category)
    {
        return categoryService.addCategory(category);
    }

    //@GetMapping(value = "/getcat")
    @GetMapping(value = "/getcat",params = {"id"})
    public ResponseEntity<Object> getCatById(@RequestParam(value = "id") Integer catId)
    {                                       //by default required=true
        try
        {
            Category category=categoryService.getCategoryById(catId);
            return new ResponseEntity<>(category,HttpStatus.OK);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getcat",params = {"name"})
    public ResponseEntity<Object> getCatByName(@RequestParam String name)
    {
        try {
            return new ResponseEntity<>(categoryService.getCategoryByName(name),HttpStatus.OK);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/getallcat")
    public List<Category> fetchAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @PostMapping("/updatecat/{id}")
    public Category updateCat(@PathVariable Integer id,@RequestBody Category category)
    {
        return categoryService.updateCategoryById(id,category);
    }

    @GetMapping("/sortbyname")
    public List<Category> sortByName()
    {
        return categoryService.sortCategoryByName();
    }

    @GetMapping("delete/{id}")
    public String deleteById(@PathVariable("id") Integer cid)
    {
        return categoryService.deleteCatById(cid);
    }

}
