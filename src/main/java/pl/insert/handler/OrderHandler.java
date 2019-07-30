package pl.insert.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.insert.dao.OrderDao;
import pl.insert.dto.OrderDto;
import pl.insert.model.Order;
import pl.insert.service.OrderService;

import javax.validation.Valid;
import java.io.Serializable;



public class OrderHandler implements Serializable {


    @Autowired
    OrderService orderService;


    public void saveOrder(@Valid OrderDto orderDto){

        OrderDto orderDto1 = new OrderDto();
        orderDto1.setFirstName(orderDto.getFirstName());


        Order order = new Order();
        order.setFirstName(orderDto.getFirstName());
        order.setSecondName(orderDto.getSecondName());

        orderService.persist(order);
    }


}
