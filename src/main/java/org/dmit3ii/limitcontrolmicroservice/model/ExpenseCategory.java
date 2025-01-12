package org.dmit3ii.limitcontrolmicroservice.model;

public enum ExpenseCategory {
    PRODUCT("PRODUCT"),
    SERVICE("SERVICE");

    private final String categoryName;

    ExpenseCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
