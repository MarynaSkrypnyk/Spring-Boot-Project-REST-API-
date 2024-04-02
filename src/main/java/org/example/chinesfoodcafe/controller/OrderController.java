package org.example.chinesfoodcafe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.Order;
import org.example.chinesfoodcafe.model.OrderRequest;
import org.example.chinesfoodcafe.service.OrderService;
import org.example.chinesfoodcafe.utils.Delivery;
import org.example.chinesfoodcafe.utils.Payment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public String create(@RequestBody @Valid OrderRequest orderRequest) {
        Order order = getOrder(orderRequest);

        log.info("create order : {}", order);

        orderService.create(order);
        return "Order accepted for work, wait for a call to confirm your order";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteOrderById(@PathVariable Long id) {
        log.info("User: {}", id);
        orderService.deleteOrderById(id);
        log.info("delete order : {}", id);
        return "Order cancel";
    }

    @GetMapping("/list")
    public List<Order> getAllOrders(@RequestParam(required = false, defaultValue = "0") int page,
                                    @RequestParam(required = false, defaultValue = "5") int size
    ) {
        log.info("getAllOrders : {}", page);
        return orderService.getAllOrders(PageRequest.of(page, size));

    }

    private Order getOrder(OrderRequest orderRequest) {
        String dish = orderRequest.getDish();
        int number = orderRequest.getNumber();
        String location = orderRequest.getLocation();
        Payment payment = orderRequest.getPayment();
        String note = orderRequest.getNote();


        String email = orderRequest.getEmail();
        Delivery delivery = orderRequest.getDelivery();
        Order order = new Order(dish, email, location, number, delivery, payment, note);
        return order;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
