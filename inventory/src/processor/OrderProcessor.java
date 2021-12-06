package processor;

import modular.Card;
import modular.Item;
import modular.Order;
import modular.OrderItem;
import storage.CardStorage;
import storage.ItemStorage;
import validator.CategoryCapHandler;
import validator.Handler;
import validator.ItemAvailabilityHandler;
import validator.StockAvailabilityHandler;
import writer.CsvWriter;

public class OrderProcessor {
    private CardStorage cardStorage = CardStorage.getInstance();
    private ItemStorage itemStorage = ItemStorage.getInstance();
    private Order currentOrder;
    StringBuilder output = new StringBuilder();
    private double total = 0;

    public OrderProcessor(Order order) {
        this.currentOrder = order;
    }

    public boolean checkValidityOfOrder() {
        checkItemStock();
        return output.length()==0;
    }

    public void calculateTotalPrice() {
        for(OrderItem item: currentOrder.getItems()){
            total += item.getQuantity()*itemStorage.getInventory().get(item.getName()).getPrice();
        }
    }

    public double getTotalPrice() {
        return total;
    }

    public void placeOrder() {
        for(OrderItem orderItem: currentOrder.getItems()){
            Item item = itemStorage.getInventory().get(orderItem.getName());
            item.setQuantity(item.getQuantity()-orderItem.getQuantity());
        }
        writeOutputFile();
    }

    public boolean checkItemStock() {
        StringBuilder sb = new StringBuilder();
        Handler itemPresence = new ItemAvailabilityHandler();
        Handler itemStock = new StockAvailabilityHandler();
        Handler itemCategory = new CategoryCapHandler();
        itemPresence.next(itemStock);
        itemStock.next(itemCategory);
        if(!itemPresence.validate(currentOrder.getItems())){
            output.append("One of the Item doesn't exist in the stock");
            output.append("\n");
        }else {
            if(!itemStock.validate(currentOrder.getItems())){
                output.append("Please correct quantities in the provided order");
                output.append("\n");
            }else if(!itemCategory.validate(currentOrder.getItems())){
                output.append("Limit on one of the Categories has exceeded");
                output.append("\n");
            }
            for(OrderItem orderItem: currentOrder.getItems()){
                Item item = itemStorage.getInventory().get(orderItem.getName());
                if(item.getQuantity()<orderItem.getQuantity()){
                    if(sb.length()>0)
                        sb.append(",");
                    sb.append(orderItem.getName()+"("+item.getQuantity()+" available)");
                }else{
                    boolean flag = true;
                    for(Card credit:cardStorage.getInventory()){
                        if(credit.getNumber().equals(orderItem.getCardNumber())){
                            flag = false;
                        }
                    }
                    if(flag){
                        Card addCard = new Card(orderItem.getCardNumber());
                        cardStorage.getInventory().add(addCard);
                    }
                }
            }
        }
        if(sb.length()>0){
            output.append("Please correct quantities - available quantity is less than requested");
            output.append("\n");
            output.append(sb.toString());
        }
        return sb.length()==0;
    }

    public void writeOutputFile(){
        CsvWriter csvWriter = new CsvWriter();
        if(output.length()==0){
            output.append("Amount Paid");
            output.append("\n");
            output.append(Double.toString(total));
            csvWriter.write("output.csv",output);
        }else{
            csvWriter.write("error.txt",output);
        }
    }
}
