package org.example.chinesfoodcafe.repository;

import org.aspectj.weaver.ast.Or;
import org.example.chinesfoodcafe.entity.Menu;
import org.example.chinesfoodcafe.entity.Order;
import org.example.chinesfoodcafe.utils.Delivery;
import org.example.chinesfoodcafe.utils.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;
    @Test
    public void get_all_dish(){
       Order order = new Order("Chicken","Mary@gmail.com","Mykolaiv",54637, Delivery.DELIVERY, Payment.CARD,"hurry up");
       Order order2 = new Order("Ice cresm","Mary2@gmail.com","Lviv",7474, Delivery.DELIVERY, Payment.CARD,"can wait");
       orderRepository.save(order);
       orderRepository.save(order2);

       Page<Order> orderPage = orderRepository.getAllOrder(PageRequest.of(0, 1));
        assertEquals(2, orderPage.getTotalElements());
    }
}
