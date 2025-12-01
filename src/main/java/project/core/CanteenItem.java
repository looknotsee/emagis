package project.core;

//inheritance and poly
public class CanteenItem extends Product {
    
    public CanteenItem(String id, String name, double price) {
        super(id, name, price);
        //need to use super to call parentclass (product's) constructor
    }

    @Override
    public String getCategoryLabel() {
        return "Canteen";
    }
}
