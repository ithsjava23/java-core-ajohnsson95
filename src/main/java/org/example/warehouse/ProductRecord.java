package org.example.warehouse;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductRecord {
    private final UUID uuid;
    private final String name;
    private final Category category;
    private BigDecimal price;
    private final BigDecimal originalPrice;

    public ProductRecord(UUID uuid, String name, Category category, BigDecimal price) {
        this.uuid = uuid;
        this.name = name;
        this.category = category;
        this.price = price;
        this.originalPrice = price;
    }
    public String getName() {
        return name;
    }
    public UUID uuid() {
        return this.uuid;
    }


    public Category category() {
        return this.category;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public BigDecimal getOriginalPrice() {
        return this.originalPrice;
    }

    public void setPrice(BigDecimal price) {
            this.price = price;

    }
}