package project.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.backend.models.Order;
import project.backend.repository.OrderRepository;
@Service
public class OrderService {
 
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order){
        return orderRepository.save(order);
    }
    // public Order deleteOrder(Order order)
    // {
    //     return orderRepository.deleteById(order.getId());
    // }
}
