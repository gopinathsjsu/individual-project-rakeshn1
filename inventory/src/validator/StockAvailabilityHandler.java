package validator;

import modular.OrderItem;
import storage.ItemStorage;

import java.util.ArrayList;

public class StockAvailabilityHandler implements Handler{
    private Handler nextValidation = null;

    @Override
    public boolean validate(ArrayList<OrderItem> items) {
        ItemStorage itemStore = ItemStorage.getInstance();
        for(OrderItem orderItem: items){
            if(itemStore.getInventory().get(orderItem.getName()).getQuantity()<orderItem.getQuantity()){
                return false;
            }
        }
        return true;
    }

    @Override
    public void next(Handler nextValidation) {
        this.nextValidation = nextValidation ;
    }
}
