package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.CatalogItem;
import com.squareup.connect.models.CatalogObject;
import com.stephenboyer.soapstore.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

//    @Id
    public String id;

    private Integer version;

    private String name;

    // Product id for entire product, not variations unless there is only one variation
    private String productId;

    private String description;
    private String imageUrl;
    private String categoryId;
    private Category category;
    private List<ProductVariation> productVariations;
    private List<VariationType> variationTypes = new ArrayList<>();


    enum VariationType {
        SIZE_OZ,
        SIZE,
        SCENT,
        NONE
    }

    private String URL;
//    private static AtomicLong idCounter = new AtomicLong(1001);

    private Product(){ }

    public Product(CatalogObject catalogObject){
        // Entire product, including variations = CatalogObject
        CatalogItem catalogItem = catalogObject.getItemData();
        List<CatalogObject> variations = catalogItem.getVariations();

        // List of variations like diff smells/flavors
        productVariations = new ArrayList<>();

        // Entire product id
        productId = catalogObject.getId();

        // Product name and description as defined in Square
        //
        name = catalogItem.getName();
        description = catalogItem.getDescription();

        // Use image coming soon placeholder if image isn't present
        // @TODO improve
        imageUrl = (catalogItem.getImageUrl() == null || catalogItem.getImageUrl().isEmpty() ? "../../images/image_coming_soon_2.png" : catalogItem.getImageUrl());

        // Category id
        categoryId = catalogItem.getCategoryId();

        // Product's URL, '/store/item/93HM93JE...'
        //ex. laramiesoapco.com/store/item/93HM93JE...
        URL =  ("/store/item/") + productId;


        try {
            variations.forEach(this::setVariation);
        } catch (NullPointerException ex){
            ex.printStackTrace(); // TODO
        }
    }

    private void setVariation(CatalogObject variation) {
        productVariations.add(new ProductVariation(variation.getItemVariationData()));

        String sku = variation.getItemVariationData().getSku();

        if(sku != null && sku.contains("oz")){
            if(!variationTypes.contains(VariationType.SIZE_OZ)){
                variationTypes.add(VariationType.SIZE_OZ);
            }
        }

//        if(sku.contains("large") || sku.contains("small"))


    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<ProductVariation> getProductVariations() {
        return productVariations;
    }

    public void setProductVariations(List<ProductVariation> productVariations) {
        this.productVariations = productVariations;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
