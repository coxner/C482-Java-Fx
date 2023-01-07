package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Creates list associatedParts
     */
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Used to get product id
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     * Set product id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get product name
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the product price
     * @return price of product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the price of the product
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the product stock
     * @return product stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * Set the product stock
     * @param stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get the product min
     * @return product min
     */
    public int getMin() {
        return min;
    }

    /**
     * Set the product min
     * @param min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get the product max
     * @return the product max
     */
    public int getMax() {
        return max;
    }

    /**
     * Set the product max
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets the associated parts list attached to a product
     * @return associatedParts list
     */
    public static ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * Add a part to a product
     * @param part
     */
    public void addAssociatedPart(ObservableList<Part> part){
        this.associatedParts.addAll(part);
    }

    /**
     * Delete a part from product
     * @param partToDelete part to be deleted from associated part
     * @return boolean value on if part deleted was successful or not
     */
    public static boolean deleteAssociatedPart(Part partToDelete) {
        if (associatedParts.contains(partToDelete)) {
            associatedParts.remove(partToDelete);
            return true;
        }
        else
            return false;
    }
}
