package storage;

import modular.Card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CardStorage {
    private Set<Card> carStore;
    private static CardStorage instance = null;

    private CardStorage() {
        carStore = new HashSet<>();
    }

    public static CardStorage getInstance(){
        if(instance==null){
            instance = new CardStorage();
        }
        return instance;
    }

    public boolean add(Card card){
        carStore.add(card);
        return true;
    }

    public Set<Card> getInventory() {
        return carStore;
    }
}
