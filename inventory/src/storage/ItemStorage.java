package storage;

import modular.Item;

import java.util.HashMap;
import java.util.Map;

public class ItemStorage {
    private Map<String, Item> itemStore;
    private static ItemStorage instance = null;

    private ItemStorage() {
        itemStore = new HashMap<>();
    }

    public static ItemStorage getInstance(){
        if(instance==null){
            instance = new ItemStorage();
        }
        return instance;
    }

    public boolean add(Item item){
        itemStore.put(item.getName(), item);
        return true;
    }

    public Map<String, Item> getInventory() {
        return itemStore;
    }
}
