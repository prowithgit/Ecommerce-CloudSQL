package com.ecommerce.brand.service;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.exception.InvalidInputException;

import java.util.List;
import java.util.Optional;

public interface BrandService
{
    Brand addBrand(Brand brand);
    List<Brand> getAllBrands();

    Optional<Brand> getById(Integer id) throws InvalidInputException;
    Brand getByName(String name) throws InvalidInputException;

    List<Brand> sortByName();

    Brand updateById(Integer id,Brand brand);

    String deleteBrand(Integer id);

}
