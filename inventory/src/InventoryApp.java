import builder.CardStorageBuilder;
import builder.ItemStorageBuilder;
import builder.OrderStorageBuilder;
import modular.Card;
import modular.Item;
import modular.Order;
import processor.OrderProcessor;
import storage.CardStorage;
import storage.ItemStorage;

import java.util.Map;

public class InventoryApp {
    public static void main(String[] args){
        String itemDataPath = args[0];
        String cardDataPath = args[1];
        String orderDataPath = args[2];

        CardStorageBuilder cardStorageBuilder = new CardStorageBuilder();
        cardStorageBuilder.build(cardDataPath);

        ItemStorageBuilder itemStorageBuilder = new ItemStorageBuilder();
        itemStorageBuilder.build(itemDataPath);

        OrderStorageBuilder orderStorageBuilder = new OrderStorageBuilder();
        Order newOrder = orderStorageBuilder.build(orderDataPath);

        OrderProcessor orderProcessor = new OrderProcessor(newOrder);
        if(orderProcessor.checkValidityOfOrder()){
            orderProcessor.calculateTotalPrice();
            if(orderProcessor.getTotalPrice()>0){
                orderProcessor.placeOrder();
                System.out.println("The total amount for the given order is $" + orderProcessor.getTotalPrice());
            }
        }else {
            System.out.println("Output(error.txt) created. Please check it");
            orderProcessor.writeOutputFile();
        }

        CardStorage cards = CardStorage.getInstance();
        cardStorageBuilder.printCardInventory(cards.getInventory());
    }
}
