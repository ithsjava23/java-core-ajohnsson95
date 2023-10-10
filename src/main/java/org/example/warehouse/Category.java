package org.example.warehouse;

public class Category {
    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        return new Category(capitalizeFirstLetter(name));
    }

    public String getName() {
        return name;
    }

    // Helper method to capitalize the first letter of a string
    private static String capitalizeFirstLetter(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    // Override equals and hashCode if needed
}