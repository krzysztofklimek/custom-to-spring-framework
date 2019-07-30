package pl.insert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.dao.OrderDao;
import pl.insert.dao.OwnerDao;
import pl.insert.dto.OrderDto;
import pl.insert.model.Order;

import java.io.Serializable;

@Service
public class OrderService implements Serializable{


    @Autowired
    OrderDao orderDao;

    @Transactional
    public void persist(Order order){
        orderDao.persist(order);

    }



}
