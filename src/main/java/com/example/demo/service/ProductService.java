package com.example.demo.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.model.Product;

@Service
public class ProductService {
    
    private final String productsFilePath = System.getenv("PRODUCTS_FILE_PATH") != null ? 
                                              System.getenv("PRODUCTS_FILE_PATH") : 
                                              "/app/data/products.txt";

    private List<Product> loadProductsFromFile() {
        List<Product> productList = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(productsFilePath))) {
            List<String> lines = reader.lines().collect(Collectors.toList());

            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    String image = parts[2].trim();

                    productList.add(new Product(name, description, image));
                } else {
                    System.err.println("Error: Invalid product format in products file");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: Cannot read products file");
            e.printStackTrace();
        }

        return productList;
    }

    public List<Product> getAllProducts() {
        return loadProductsFromFile();
    }

    public List<Product> searchProducts(String query) {
        List<Product> allProducts = loadProductsFromFile();
        List<Product> matchingProducts = new ArrayList<>();
        
        for (Product product : allProducts) {
            if (product.matches(query)) {
                matchingProducts.add(product);
            }
        }
        
        return matchingProducts;
    }

    public boolean addProduct(Product product) {
        if (product == null) {
            System.err.println("Error: Product cannot be null");
            return false;
        }
        
        if (product.getName() == null || product.getDescription() == null || product.getImage() == null) {
            System.err.println("Error: Product has missing fields");
            return false;
        }
    
        List<Product> products = loadProductsFromFile();
        
        int insertPosition = 0;
        while (insertPosition < products.size() && 
               products.get(insertPosition).getName().compareToIgnoreCase(product.getName()) < 0) {
            insertPosition++;
        }
        products.add(insertPosition, product);
        
        try {
            FileWriter fw = new FileWriter(productsFilePath);
            BufferedWriter writer = new BufferedWriter(fw);
            
            for (Product p : products) {
                String line = p.getName() + "," + p.getDescription() + "," + p.getImage();
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            return true;
        } catch (IOException e) {
            System.err.println("Error: Cannot write to products file");
            e.printStackTrace();
            return false;
        }
    }
}
