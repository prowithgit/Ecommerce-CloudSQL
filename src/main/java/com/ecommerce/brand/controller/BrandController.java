package com.ecommerce.brand.controller;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.brand.repository.BrandRepository;
import com.ecommerce.brand.service.BrandService;
import com.ecommerce.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class BrandController
{
    @Autowired
    @Qualifier("brandServiceImpl")
    private BrandService brandService;

    @GetMapping("/")
    String hello()
    {
        return "hello World";
    }

    @PostMapping("/addbrand")
    Brand createNewBrand(@RequestBody Brand brand)
    {
        log.info(brand.getCid()+" ");
        return brandService.addBrand(brand);
    }

    @GetMapping("/getall")
    public List<Brand> fetchAll()
    {
        return brandService.getAllBrands();
    }

    @GetMapping(value = "/getbrand",params = {"id"})
    public ResponseEntity<Object> fetchById(@RequestParam Integer id)
    {
        try
        {
            Optional<Brand> brand=brandService.getById(id);
            Brand brand1=brand.get();
            return new ResponseEntity<>(brand1,HttpStatus.OK);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getbrand",params = {"name"})
    public ResponseEntity<Object> fetchByName(@RequestParam String name)
    {
        try
        {
            Brand brand = brandService.getByName(name);
            return new ResponseEntity<>(brand,HttpStatus.NOT_FOUND);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<Object>(exception.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("sortname")
    public List<Brand> sortingName()
    {
        return brandService.sortByName();
    }

    @PostMapping("updatebrand/{id}")
    public Brand updateBrand(@PathVariable Integer id,@RequestBody Brand brand)
    {
        return brandService.updateById(id,brand);
    }

    @GetMapping("/deletebrand/{id}")
    public String deleteBrand(@PathVariable Integer id)
    {
        return brandService.deleteBrand(id);
    }
}
