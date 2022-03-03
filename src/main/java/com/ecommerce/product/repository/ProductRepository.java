package com.ecommerce.product.repository;

import com.ecommerce.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>
{
    Product findByPname(String name);

    @Query("select p.prodId from Product p")
    List<Integer> findProdId();

    @Query("select p.pname from Product p")
    List<String> findPname();
}
