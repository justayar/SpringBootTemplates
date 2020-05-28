package com.justayar.springboot.util.tostring;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductSubCategory extends ProductCategory {

    private int subCategoryId;
    @ToString.Include(name="Sub Category Name",rank=2)
    private String subCategoryName;

}
