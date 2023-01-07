package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class Inventory {
    /**
     * Method to add part to allParts list
     * @param part
     */
    public static void addPart(Part part) {
        allParts.add(part);
    }

    /**
     * method to add product to allProducts list
     * @param product
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Initialize allParts ObservableList
     */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    /**
     * Initialize allProducts ObservableList
     */
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Used to access all parts in the list
     * @return gets all parts from the allParts list
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Used to access all products in the list
     * @return gets all products from the allProducts list
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * Used to update the product when it is modified
     * @param id
     * @param prodToUpdate
     */
    public static void updateProduct(int id, Product prodToUpdate) {
        allProducts.set(id, prodToUpdate);
    }

    /**
     * Used to update the part when it is modified
     * @param id
     * @param partToUpdate
     */
    public static void updatePart(int id, Part partToUpdate) {allParts.set(id, partToUpdate);}

    /**
     * Used to search for a part id
     * @param partId
     * @return a part id
     */
    public static Part lookupPart(int partId) {
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(int i = 0; i < allParts.size(); i++) {
            Part pId = allParts.get(i);

            if(pId.getId() == partId) {
                // return one or none
                return pId;
            }
        }
        // if no matches return null
        return null;

    }

    /**
     * Used to search for a product by name
     * @param prodName
     * @return a product name if it is found
     */
    public static ObservableList<Product> lookupProduct(String prodName) {
        ObservableList<Product> namedProd = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for(Product p : allProducts) {
            if(p.getName().contains(prodName)){
                namedProd.add(p);
            }
        }
        return namedProd;
    }

    /**
     * Used to search for a product Id
     * @param prodId
     * @return a product id
     */
    public static Product lookupProduct(int prodId) {
        ObservableList<Product> allProd = Inventory.getAllProducts();
        for(int i = 0; i < allProducts.size(); i++) {
            Product pId = allProducts.get(i);
            if(pId.getId() == prodId) {
                // return one or none
                return pId;
            }
        }
        // if no matches return null
        return null;
    }

    /**
     * Used to search for a part by name
     * @param partName
     * @return a part name if found
     */
    public static ObservableList<Part> lookupPart(String partName) {
        // creates a empty observable array list
        ObservableList<Part> namedParts = FXCollections.observableArrayList();
        // fetch all players
        ObservableList<Part> allParts = Inventory.getAllParts();
        // for loop that loops list with temporary variable bp
        for(Part p : allParts) {
            // returns a boolean value if a empty string is searched contain returns full data list
            if(p.getName().contains(partName)){
                namedParts.add(p);
            }
        }
        return namedParts;
    }

    /**
     * Used to generate a new partId
     * @return generates a part id
     */
    public static int getNewPartID(){
        int newID = 1;
        for (int i = 0; i < Inventory.getAllParts().size(); i++) {
            newID++;
        }
        return newID;
    }

    /**
     * Used to generate a product Id
     * @return a generated product Id
     */
    public static int getNewProductID(){
        int newID = 1;
        for (int i = 0; i < Inventory.getAllProducts().size(); i++) {
            newID++;
        }
        return newID;
    }

    /**
     * Used to delete part if part exist
     * @param partToDelete
     * @return true or false value if part exist in allParts
     */
    public static boolean deletePart(Part partToDelete) {
        if (allParts.contains(partToDelete)) {
            allParts.remove(partToDelete);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Used to delete a product if it exist
     * @param productToDelete
     * @return true or false if product exist in allProducts
     */
    public static boolean deleteProduct(Product productToDelete) {
        if (allProducts.contains(productToDelete)) {
            allProducts.remove(productToDelete);
            return true;
        }
        else {
            return false;
        }
    }
}



