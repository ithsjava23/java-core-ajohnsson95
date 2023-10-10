package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;

public class Warehouse {
    private String name;
    private static List<ProductRecord> products = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
    }

    private Warehouse() {}
    public static Warehouse getInstance(String name) {
        return new Warehouse(name);
    }

    public static Warehouse getInstance() {
        return new Warehouse();
    }


    public ProductRecord addProduct(UUID uuid, String name, Category category, BigDecimal price) {
        if (name == null) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) == 0) {
            price = BigDecimal.ZERO;
        }
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }

        ProductRecord product = new ProductRecord(uuid, name, category, price);
        products.add(product);
        return product;
    }

    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Optional<ProductRecord> optionalProduct = getProductById(id);

        if (optionalProduct.isPresent()) {
            ProductRecord existingProduct = optionalProduct.get();
            existingProduct.setPrice(newPrice);
        } else {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID can't be null.");
        }
        return products.stream().filter(product -> id.equals(product.uuid())).findFirst();
    }

    public List<ProductRecord> getChangedProducts() {
        List<ProductRecord> changedProducts = new ArrayList<>();

        for (ProductRecord product : products) {
            BigDecimal currentPrice = product.getPrice();
            BigDecimal originalPrice = product.getOriginalPrice();

            if (originalPrice != null && !currentPrice.equals(originalPrice)) {
                changedProducts.add(product);
            }
        }
        return Collections.unmodifiableList(changedProducts);
    }

    public static Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        Map<Category, List<ProductRecord>> productsOfCategory = new HashMap<>();

        for (ProductRecord product : products) {
            Category category = product.category();

            List<ProductRecord> productsCategory = productsOfCategory.getOrDefault(category, new ArrayList<>());
            productsCategory.add(product);

            productsOfCategory.put(category, productsCategory);
        }
        return Collections.unmodifiableMap(productsOfCategory);
    }

    public List<ProductRecord> getProductsBy(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");
        }
        List<ProductRecord> productsInCategory = new ArrayList<>();
        for (ProductRecord product : products) {
            if (category.equals(product.category())) {
                productsInCategory.add(product);
            }
        }
        return Collections.unmodifiableList(productsInCategory);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    // Other methods and fields as needed

    // Override equals and hashCode if needed
}