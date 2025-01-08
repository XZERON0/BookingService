package project.backend.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import project.backend.models.Order;
import project.backend.models.Provider;
import project.backend.models.User;
import project.backend.repository.OrderRepository;
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
public void testGetOrderWithRelations() throws Exception {
    User customer = new User();
    customer.setId(1L);
    customer.setName("John Doe");
    Provider provider = new Provider();
    provider.setId(1L);

    Order order = new Order();
    order.setId(1L);
    order.setCustomer(customer);
    order.setProvider(provider);
    order.setCreatedAt(LocalDateTime.now());

    when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

    mockMvc.perform(get("/order/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.customer.name").value("John Doe"))
            .andExpect(jsonPath("$.provider.name").value("Provider X"));
}


    @Test
    public void testPostOrder() throws Exception {
        Order order = new Order();
        order.setId(1L);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(order)))
                .andExpect(status().isOk())
                .andExpect(content().string("success"));
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order order1 = new Order();
        order1.setId(1L);

        Order order2 = new Order();
        order2.setId(2L);

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        mockMvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
                }
}
