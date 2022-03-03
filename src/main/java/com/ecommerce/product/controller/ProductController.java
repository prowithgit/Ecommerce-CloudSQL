package com.ecommerce.product.controller;

import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
public class ProductController
{
    @Autowired
    @Qualifier("productServiceImpl")
    private ProductService productService;

    @PostMapping("/addprod")
    public Product createNewProduct(@RequestBody Product product)
    {
        double mp=product.getMarkedPrice();
        double disc=product.getDiscount();
        double finalPrice=mp-((disc/100)*mp);

        product.setFinalPrice(finalPrice);
        long millis=System.currentTimeMillis();
        // creating a new object of the class Date
        Date date = new Date(millis);
        product.setCreatedTime(date);
        return productService.addProduct(product);
    }

    @GetMapping(value = "/get",params = {"id"})
    public ResponseEntity<Object> fetchById(@RequestParam Integer id)
    {
        try
        {
            Product product=productService.getById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/get",params = {"name"})
    public ResponseEntity<Object> fetchByName(@RequestParam String name)
    {
        try
        {
           Product product= productService.getByName(name);
            return new ResponseEntity<Object>(product,HttpStatus.OK);
        }
        catch (InvalidInputException exception)
        {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.OK);
        }

    }

    @GetMapping("allprod")
    public List<Product> fetchAll()
    {
        return productService.getAll();
    }

    @PostMapping("updateprod/{id}")
    public Product updateProd(@PathVariable Integer id,@RequestBody Product product)
    {
        System.out.println("In update");
       return productService.updateProdById(id,product);
    }

    @GetMapping("sortprod")
    public List<Product> sortProduct()
    {
        return productService.sortByName();
    }

    @GetMapping(value = "prodbycatid",params = {"catid"})
    public List<Product> productByCatId(@RequestParam Integer catid)
    {
        return productService.getbyCategoryId(catid);
    }

    @GetMapping(value = "prodbybrandid",params = {"brandid"})
    public List<Product> productByBrandId(@RequestParam("brandid") Integer bid)
    {
        return productService.getbyBrandId(bid);
    }

    @GetMapping(value = "prodbycatname",params = {"catname"})
    public List<Product> productByCatname(@RequestParam("catname") String name)
    {
        return productService.getbyCategoryName(name);
    }

    @GetMapping(value = "prodbybrandname",params = {"brandname"})
    public List<Product> productByBrandName(@RequestParam("brandname") String name)
    {
        return productService.getbyBrandName(name);
    }

}
