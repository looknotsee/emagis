package project.core;

//inheritance and poly
public class BookItem extends Product {
    public BookItem(String id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public String getCategoryLabel() {
        return "Bookcenter";
    }
}
