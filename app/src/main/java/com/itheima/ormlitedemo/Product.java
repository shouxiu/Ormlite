package com.itheima.ormlitedemo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @创建者 Adminstration
 * @创建时间 2016/11/3 13:44
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

@DatabaseTable(tableName = "product")
public class Product {
    @DatabaseField(generatedId = true)
    public int id;
    @DatabaseField(columnName = "productId", unique = true)
    public int productId;
    @DatabaseField(columnName = "name")
    public String name;

    public Product() {
    }

    public Product(int id, int productId, String name) {
        this.id = id;
        this.productId = productId;
        this.name = name;
    }

    //假数据
    public Product(int productId){
        this.productId = productId;
        this.name = "product"+productId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", produceId=" + productId +
                ", name='" + name + '\'' +
                '}';
    }
}
