package validator;

import modular.OrderItem;

import java.util.ArrayList;

public interface Handler {
    boolean validate(ArrayList<OrderItem> items);
    void next(Handler next);
}
