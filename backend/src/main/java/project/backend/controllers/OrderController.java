package project.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.backend.DTO.OrderDTO;
import project.backend.models.Order;
import project.backend.repository.OrderRepository;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository rep;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long id)
    {
        Optional<Order> order = rep.findById(id);
        return ResponseEntity.ok(new OrderDTO(order.get().getId(), null, null, order.get().getCreatedAt()));
    }
    @PostMapping
    public ResponseEntity<String> postOrder(@RequestBody Order order)
    {
        rep.save(order);
        return ResponseEntity.ok("success");
    }
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrder()
    {
        List<Order> orders = rep.findAll();
        List<OrderDTO> orderDTOs = orders.stream()
                .map(order -> new OrderDTO(order.getId(), null, null, order.getCreatedAt()))
                .toList();
        return ResponseEntity.ok(orderDTOs);
    }
}