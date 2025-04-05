package com.example.demo.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private String image;
    
    public Product() {
    }

    public Product(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    public boolean matches(String query) {
        if (query == null || query.isEmpty()) {
            return true; 
        }
        
        String lowerQuery = query.toLowerCase();
        return name.toLowerCase().contains(lowerQuery) || 
               description.toLowerCase().contains(lowerQuery);
    }
}
