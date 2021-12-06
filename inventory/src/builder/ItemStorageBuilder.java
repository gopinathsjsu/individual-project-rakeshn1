package builder;

import modular.Item;
import reader.ItemsCsvReader;
import storage.ItemStorage;

import java.util.Map;

public class ItemStorageBuilder {
    public ItemStorage build(String dataPath){
        System.out.println("Building Item Storage!");
        ItemStorage storage = ItemStorage.getInstance();
        ItemsCsvReader reader = new ItemsCsvReader();
        int count = 0;
        reader.open(dataPath);
        reader.readHeader();
        Item item;
        while((item = reader.read()) != null){
            count++;
            storage.add(item);
        }
        System.out.println(count+" items added.");
        printItemStorage(storage.getInventory());
        System.out.println("Item Storage done successfully!");
        return storage;
    }

    public void printItemStorage(Map<String, Item> map){
        for (Map.Entry<String, Item> set :
                map.entrySet()) {

            System.out.println(set.getKey() + " = "
                    + set.getValue());
        }
    }
}
