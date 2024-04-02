package org.example.chinesfoodcafe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.Order;
import org.example.chinesfoodcafe.exeption.NoDishException;
import org.example.chinesfoodcafe.exeption.OrderException;
import org.example.chinesfoodcafe.repository.MenuRepository;
import org.example.chinesfoodcafe.repository.OrderRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Cacheable(value = "order")
public class OrderService {
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final VerificationService verificationService;

    @Cacheable(value = "order", key = "#order.email")
    public Order create(Order order) {
        if (!verificationService.isUserVerified(order.getEmail())) {
            throw new OrderException();
        }
        if (!menuRepository.existsByDish(order.getDish())) {
            throw new NoDishException();
        }
        log.info("create order: {}", order);
        return orderRepository.save(order);
    }

    @CacheEvict(value = "order")
    public String deleteOrderById(Long id) {
        orderRepository.deleteById(id);

        log.info("delete order: {}", id);
        return "Order cansel";
    }

    public List<Order> getAllOrders(PageRequest pageRequest) {
        Page<Order> page = orderRepository.getAllOrder(pageRequest);
        log.info("pageRequest getAllOrders: {}", pageRequest);
        return page.getContent();
    }
}
