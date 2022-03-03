package com.ecommerce.product.service;

import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.product.model.Product;

import java.util.List;

public interface ProductService
{
    Product addProduct(Product product);

    Product getById(Integer id) throws InvalidInputException;
    Product getByName(String name)throws InvalidInputException;
    List<Product> getAll();

    Product updateProdById(Integer id,Product product);
    List<Product> sortByName();
    List<Product> getbyCategoryId(Integer cid);
    List<Product> getbyCategoryName(String name);
    List<Product> getbyBrandId(Integer bid);
    List<Product> getbyBrandName(String name);
}
