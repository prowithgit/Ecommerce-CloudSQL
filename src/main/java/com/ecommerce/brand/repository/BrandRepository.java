package com.ecommerce.brand.repository;

import com.ecommerce.brand.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer>
{
    Brand findByBname(String name);

    @Query("select b.brandId from Brand b")
    List<Integer> findBrandIds();

    @Query("select b.bname from Brand b")
    List<String> findBname();
}
