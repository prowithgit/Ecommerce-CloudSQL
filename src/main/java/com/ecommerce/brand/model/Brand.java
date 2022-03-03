package com.ecommerce.brand.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table
@Slf4j
public class Brand implements Comparable<Brand>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    @Column(unique = true)
    private String bname;
    private int cid;
    private String logoUrl;

    @Override
    public int compareTo(Brand b)
    {
        //String and Wrapper Clases are by default Comparable.So have their own compareTo().
        return this.bname.compareTo(b.bname);
    }
}
