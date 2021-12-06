package builder;

import modular.Card;
import reader.CardCsvReader;
import storage.CardStorage;

import java.util.Set;

public class CardStorageBuilder {
    public CardStorage build(String dataPath){
        System.out.println("Building Card Storage!");
        CardStorage storage = CardStorage.getInstance();
        CardCsvReader reader = new CardCsvReader();
        Card card;
        int count = 0;
        reader.open(dataPath);
        reader.readHeader();
        while((card = reader.read()) != null){
            count++;
            storage.add(card);
        }

        System.out.println(count+" cards added.");
        printCardInventory(storage.getInventory());
        System.out.println("Card Storage done successfully!");
        return storage;
    }

//    public CardStorageBuilder setDataPath(String dataPath) {
//        this.dataPath = dataPath;
//        return this;
//    }

    public void printCardInventory(Set<Card> set){
        for (Card card :
                set ) {
            System.out.println(card);
        }
    }
}
