package modular;

public class OrderItem {
    String name;
    int quantity;
    String cardNumber;

    public OrderItem(String name, int quantity, String cardNumber) {
        this.name = name;
        this.quantity = quantity;
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "OrderItem : {" +
                "name : '" + name + '\'' +
                ", quantity : " + quantity +
                ", cardNumber : '" + cardNumber + '\'' +
                '}';
    }
}
