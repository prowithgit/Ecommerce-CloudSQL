package com.ecommerce.product.service;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.brand.repository.BrandRepository;
import com.ecommerce.category.model.Category;
import com.ecommerce.category.repository.CategoryRepository;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.*;

@Service
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Product addProduct(Product product)
    {
       return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) throws InvalidInputException
    {
        List<Integer> listID=productRepository.findProdId();
        if(listID.contains(id))
        {
            Optional<Product> product = productRepository.findById(id);
            return product.get();
        }
        else
        {
            throw new InvalidInputException("Product with id:"+id+" not available");
        }
    }

    @Override
    public Product getByName(String name) throws InvalidInputException
    {
        List<String> listName=productRepository.findPname();
        boolean status=false;
        for (String pname:listName)
        {
            if (pname.equalsIgnoreCase(name))
            {
                status=true;
                return productRepository.findByPname(name);
            }
        }
        if(status==false)
            throw new InvalidInputException("Product with name:"+name+" not available");
        return null;
    }

    @Override
    public List<Product> getAll()
    {
        return productRepository.findAll();
    }

    @Override
    public Product updateProdById(Integer id, Product product)
    {
        Optional<Product> product1=productRepository.findById(id);
        Product product2=product1.get();
        log.info("Product:",product);
        product2.setPname(product.getPname());
        product2.setShortDesc(product.getShortDesc());
        product2.setFullDesc(product.getFullDesc());
        product2.setEnabled(product.getEnabled());
        product2.setInStock(product.getInStock());
        product2.setDiscount(product.getDiscount());
        product2.setMarkedPrice(product.getMarkedPrice());
        product2.setBrandId(product.getBrandId());
        product2.setCatId(product.getCatId());
        long millis=System.currentTimeMillis();
        // creating a new object of the class Date
        Date date = new Date(millis);
        product2.setUpdatedTime(date);
        log.info("Product2:",product2);
        return productRepository.save(product2);
    }

    @Override
    public List<Product> sortByName()
    {
        List<Product> list=productRepository.findAll();
        Collections.sort(list);
        return list;
    }

    @Override
    public List<Product> getbyCategoryId(Integer cid)
    {
        List<Product> liP=productRepository.findAll();
        List<Product> catBasedList=new ArrayList<>();
        for (Product p:liP)
        {
            if(p.getCatId()==cid)
            {
                catBasedList.add(p);
            }
        }
        return catBasedList;
    }

    @Override
    public List<Product> getbyCategoryName(String name)
    {
        Category category=categoryRepository.findByCatName(name);
        int cid=category.getCatId();
        List<Product> lP=productRepository.findAll();
        List<Product> catBasedList=new ArrayList<>();
        for (Product p:lP)
        {
            if(p.getCatId()==cid)
            {
                catBasedList.add(p);
            }
        }
        return catBasedList;
    }

    @Override
    public List<Product> getbyBrandId(Integer bid)
    {
        List<Product> lP=productRepository.findAll();
        List<Product> brandBasedList=new ArrayList<>();
        for (Product p:lP)
        {
            if(p.getBrandId()==bid)
            {
                brandBasedList.add(p);
            }
        }
        return brandBasedList;
    }

    @Override
    public List<Product> getbyBrandName(String name)
    {
        Brand brand =brandRepository.findByBname(name);
        int bid=brand.getBrandId();
        List<Product> lP=productRepository.findAll();
        List<Product> brandBasedList=new ArrayList<>();
        for (Product p:lP)
        {
            if(p.getBrandId()==bid)
            {
                brandBasedList.add(p);
            }
        }
        return brandBasedList;
    }


}
