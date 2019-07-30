package pl.insert.dao;

import pl.insert.model.Order;
import pl.insert.model.Owner;

public interface OrderDao {

    void persist(Order order);
}
