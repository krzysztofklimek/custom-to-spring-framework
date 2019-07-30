package pl.insert.dao;

import pl.insert.model.Order;
import pl.insert.model.Owner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class OrderDaoImpl implements OrderDao, Serializable {


    @PersistenceContext
    private EntityManager entityManager;

    public void persist(Order order) {
        entityManager.persist(order);
    }
}
