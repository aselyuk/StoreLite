package org.selyuk.storelite.models;

public class Product {
    int id;
    String name;
    String articleNumber;
    String description;
    String outerGUID;

    public Product() {

    }

    public Product(String name) {
        this.name = name;
        this.articleNumber = articleNumber;
    }

    public Product(String name, String articleNumber) {
        this.name = name;
        this.articleNumber = articleNumber;
    }

    public Product(String name, String articleNumber, String outerGUID) {
        this.name = name;
        this.articleNumber = articleNumber;
        this.outerGUID = outerGUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOuterGUID() {
        return outerGUID;
    }

    public void setOuterGUID(String outerGUID) {
        this.outerGUID = outerGUID;
    }
}
