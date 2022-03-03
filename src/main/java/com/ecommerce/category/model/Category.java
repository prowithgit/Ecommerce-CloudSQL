package com.ecommerce.category.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Table(name = "Category")
@Setter
@Getter
@ToString
@Slf4j
public class Category implements Comparable<Category>
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catId;

    @Column(unique = true)
    private String catName;
    private String alias;
    private String imageUrl;
    private int enabled;
    private int parentId;

    @Override
    public int compareTo(Category category)
    {
        log.info("In compareTo");
        return this.catName.compareTo(category.catName);
    }
}
