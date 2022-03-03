package com.ecommerce.product.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table
@Setter
@Getter
@ToString
@Slf4j
public class Product implements Comparable<Product>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodId;

    @Column(unique = true)
    private String pname;
    private String shortDesc;
    private String fullDesc;
    private String imgUrl;
    private int enabled;
    private int inStock;
    private double markedPrice;
    private double discount;
    private double finalPrice;
    private Date createdTime;
    private Date updatedTime;
    private int brandId;
    private int catId;
    private double costPrice;
    private double avgRating;
    private int reviewCount;
    private int width,height;

    @Override
    public int compareTo(Product p)
    {
        //sorting by name(String)
        return this.pname.compareTo(p.pname);
    }


}
