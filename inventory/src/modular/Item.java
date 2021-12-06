package modular;

public class Item {
    private double price;
    private int quantity;
    private String category;
    private String name;

    public Item(String category, String name, double price, int quantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && category.equals(item.category) && name.equals(item.name);
    }

    @Override
    public String toString() {
        return "Item : {" +
                "category : '" + category + '\'' +
                ", name : '" + name + '\'' +
                ", price : " + price +
                ", quantity : " + quantity +
                '}';
    }
}
