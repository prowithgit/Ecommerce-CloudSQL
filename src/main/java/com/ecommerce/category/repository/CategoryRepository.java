package com.ecommerce.category.repository;

import com.ecommerce.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>
{
   Category findByCatName(String name);

   @Query("select c.catId from Category c")
   List<Integer> findCatId();

   @Query("select c.catName from Category c")
   List<String> findCatName();
}
