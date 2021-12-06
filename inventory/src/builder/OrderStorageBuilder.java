package builder;

import modular.Order;
import modular.OrderItem;
import reader.OrderCsvReader;

public class OrderStorageBuilder {
    public Order build(String dataPath){
        System.out.println("Reading order!");
        Order order = new Order();
        OrderCsvReader reader = new OrderCsvReader();
        int count = 0;
        reader.open(dataPath);
        reader.readHeader();
        OrderItem item;
        while((item = reader.read()) != null){
            count++;
            order.addItem(item);
        }
        System.out.println(count+" items added.");
        System.out.println("Order read!");
        return order;
    }

//    public OrderStorageBuilder setDataPath(String dataPath) {
//        this.dataPath = dataPath;
//        return this;
//    }
}
