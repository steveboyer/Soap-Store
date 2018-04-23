package com.stephenboyer.soapstore.domain;

import com.squareup.connect.models.CatalogItem;
import com.squareup.connect.models.CatalogObject;
import com.stephenboyer.soapstore.controller.CartControllerRest;
import com.stephenboyer.soapstore.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Product {

    private final transient Logger log = LoggerFactory.getLogger(Product.class.getSimpleName());

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
    private Boolean imageComingSoon = false;
    private List<ProductVariation> productVariations;
    private Boolean variationOz = false;
    private List<String> variationOzVals = new ArrayList<>();
    private Boolean variationLgSm = false;
    private List<String> variationLgSmVals = new ArrayList<>();

    private String url;
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
        if(catalogItem.getImageUrl() == null || catalogItem.getImageUrl().isEmpty()){
            imageUrl = "/images/Image_coming_soon_2.png";
            imageComingSoon = true;
        } else {
            imageUrl = catalogItem.getImageUrl();
        }

//        imageUrl = (catalogItem.getImageUrl() == null || catalogItem.getImageUrl().isEmpty() ? "/images/image_coming_soon_2.png" : catalogItem.getImageUrl());

        // Category id
        categoryId = catalogItem.getCategoryId();

        // Product's URL, '/store/item/93HM93JE...'
        //ex. laramiesoapco.com/store/item/93HM93JE...
        url =  ("/store/item/") + productId;


        try {
            variations.forEach(this::setVariation);
        } catch (NullPointerException ex){
            ex.printStackTrace(); // TODO
        }

    }

    public List<String> getVariationOzVals() {
        return variationOzVals;
    }

    public void setVariationOzVals(List<String> variationOzVals) {
        this.variationOzVals = variationOzVals;
    }

    public Boolean getVariationLgSm() {
        return variationLgSm;
    }

    public void setVariationLgSm(Boolean variationLgSm) {
        this.variationLgSm = variationLgSm;
    }

    public List<String> getVariationLgSmVals() {
        return variationLgSmVals;
    }

    public void setVariationLgSmVals(List<String> variationLgSmVals) {
        this.variationLgSmVals = variationLgSmVals;
    }

    private void setVariation(CatalogObject variation) {
        productVariations.add(new ProductVariation(variation.getItemVariationData()));

        String sku = variation.getItemVariationData().getSku();

        if(sku != null && sku.contains("oz")){
          variationOz = true;
          variationOzVals.add(sku.split("_")[2]);
        }

        if(sku != null && (sku.contains("large") || sku.contains("small"))){
            variationLgSm = true;
            variationOzVals.add(sku.split("_")[2]);
        }
    }

    public Boolean getVariationOz() {
        return variationOz;
    }

    public void setVariationOz(Boolean variationOz) {
        this.variationOz = variationOz;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getImageComingSoon() {
        return imageComingSoon;
    }

    public void setImageComingSoon(Boolean imageComingSoon) {
        this.imageComingSoon = imageComingSoon;
    }

    @Override
    public String toString() {
        return Strings.toString(this);
    }
}
