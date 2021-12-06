package validator;

import modular.OrderItem;
import storage.ItemStorage;

import java.util.ArrayList;

public class ItemAvailabilityHandler implements Handler {
    private Handler nextHandler = null;

    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        ItemStorage itemStore = ItemStorage.getInstance();
        for(OrderItem orderItem: items){
            if(!itemStore.getInventory().containsKey(orderItem.getName())){
                return false;
            }
        }
        return true;
    }

    @Override
    public void next(Handler nextHandler) {
        this.nextHandler = nextHandler ;
    }
}
