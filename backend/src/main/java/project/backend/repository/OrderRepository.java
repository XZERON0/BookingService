package project.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import project.backend.models.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}