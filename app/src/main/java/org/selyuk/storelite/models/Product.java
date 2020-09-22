package org.selyuk.storelite.models;

import com.orm.SugarRecord;

import java.util.UUID;

public class Product extends SugarRecord {
    long id;
    public String GUID = UUID.randomUUID().toString();
    public String name;
    public String articleNumber;
    String description;
    String outerGUID;

    public Product () {
    }

    public Product (String name, String articleNumber, String outerGUID) {
        this.name = name;
        this.articleNumber = articleNumber;
        this.outerGUID = outerGUID;
    }

    public Product (String name, String articleNumber) {
        this.name = name;
        this.articleNumber = articleNumber;
    }

    public Product (String name) {
        this.name = name;
    }

}
