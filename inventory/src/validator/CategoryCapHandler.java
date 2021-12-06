package validator;

import modular.OrderItem;
import storage.ItemStorage;

import java.util.ArrayList;
import java.util.HashMap;

public class CategoryCapHandler implements Handler{
    private Handler nextHandler= null;

    private final int luxury = 3;
    private final int essential = 5;
    private final int miscellaneous = 6;

    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        HashMap<String,Integer> categoryMap = new HashMap<>();
        ItemStorage itemStore = ItemStorage.getInstance();
        for(OrderItem orderItem: items){
            categoryMap.put(itemStore.getInventory().get(orderItem.getName()).getCategory(),categoryMap.getOrDefault(itemStore.getInventory().get(orderItem.getName()).getCategory(),0)+orderItem.getQuantity());
        }
        if(categoryMap.getOrDefault("Luxury",0)>luxury){
            return false;
        }else if(categoryMap.getOrDefault("Essential",0)>essential){
            return false;
        }else if(categoryMap.getOrDefault("Misc",0)>miscellaneous){
            return false;
        }
        return true;
    }
    @Override
    public void next(Handler nextHandler) {
        this.nextHandler = nextHandler ;
    }
}
