package com.justayar.springboot.util.tostring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class ProductCategory {

    private int categoryId;
    @ToString.Include(name="Main Category Name")
    private String categoryName;

}
