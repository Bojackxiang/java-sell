package com.alex.java.dataObject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;

    public ProductCategory() {

    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
