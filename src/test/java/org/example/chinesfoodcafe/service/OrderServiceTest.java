package org.example.chinesfoodcafe.service;

import org.example.chinesfoodcafe.entity.Menu;
import org.example.chinesfoodcafe.entity.Order;
import org.example.chinesfoodcafe.repository.MenuRepository;
import org.example.chinesfoodcafe.repository.OrderRepository;
import org.example.chinesfoodcafe.utils.Delivery;
import org.example.chinesfoodcafe.utils.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private VerificationService verificationService;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void create_order(){
        Order order = new Order("Chicken","Mary@gmail.com","Mykolaiv",54637, Delivery.DELIVERY, Payment.CARD,"hurry up");
        when(verificationService.isUserVerified(order.getEmail())).thenReturn(true);
        when(menuRepository.existsByDish(order.getDish())).thenReturn(true);
        when(orderRepository.save(order)).thenReturn(order);
        Order order1 = orderService.create(order);

        assertEquals(order, order1);
    }
    @Test
    public void delete_order_by_id(){
        Long id = 1L;
        orderService.deleteOrderById(id);
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    public void get_all_order(){
        PageRequest pageRequest = PageRequest.of(0, 10);
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order("Chicken","Mary@gmail.com","Mykolaiv",54637, Delivery.DELIVERY, Payment.CARD,"hurry up"));
        orderList.add(new Order("Langust","Mary@gmail.com","Mykolaiv",54637, Delivery.DELIVERY, Payment.CARD,"hurry up"));
        Page<Order> page = new PageImpl<>(orderList);

        when(orderRepository.getAllOrder(pageRequest)).thenReturn(page);
        List<Order> result = orderService.getAllOrders(pageRequest);
        assertEquals(orderList, result);

    }
}

