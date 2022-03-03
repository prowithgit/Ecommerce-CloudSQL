package com.ecommerce.brand.service;

import com.ecommerce.brand.model.Brand;
import com.ecommerce.brand.repository.BrandRepository;
import com.ecommerce.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BrandServiceImpl implements BrandService
{
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Brand addBrand(Brand brand)
    {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands()
    {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getById(Integer id) throws InvalidInputException
    {
        List<Integer> list=brandRepository.findBrandIds();
        if(list.contains(id))
        {
            return brandRepository.findById(id);
        }
        else
        {
            throw new InvalidInputException("Brand with id:"+id+" not available.");
        }

    }

    @Override
    public Brand getByName(String name) throws InvalidInputException
    {
        List<String> bList=brandRepository.findBname();
        boolean status=false;
        for (String bname:bList)
        {
         if(bname.equalsIgnoreCase(name))
        {
            status=true;
            return brandRepository.findByBname(name);
        }

        }
        if(status==false)
            throw new InvalidInputException("Brand with name:"+name+" not available");

        return null;
    }

    @Override
    public List<Brand> sortByName()
    {
        List<Brand> list=brandRepository.findAll();
        Collections.sort(list);
        return list;
    }

    @Override
    public Brand updateById(Integer id,Brand brand)
    {
        Optional<Brand> brand1=brandRepository.findById(id);
        Brand brand2=brand1.get();

        brand2.setBname(brand.getBname());
        brand2.setLogoUrl(brand.getLogoUrl());
        brand2.setCid(brand.getCid());
        return brandRepository.save(brand2);
    }

    @Override
    public String deleteBrand(Integer id)
    {
        brandRepository.deleteById(id);
        return "Brand deleted";
    }
}
