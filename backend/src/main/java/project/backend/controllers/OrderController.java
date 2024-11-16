package project.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.backend.repository.OrderRepository;
import project.backend.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    
    // @GetMapping
    // public ResponseEntity<String> getOrder(Order order)
    // {
    //     Optional order = orderRepository.findAll(order.);
    //     return ;
    // }
}
