

import Item;
import Employee;
import java.time.LocalDateTime;

public abstract class Order {
    ArrayList<Item> items;
    Employee employee;
    float totalPrice;
    LocalDateTime timeOfSale;

    public Order() {
        throw new Error("Unresolved compilation problem: \n\tArrayList cannot be resolved to a type\n");
    }
}