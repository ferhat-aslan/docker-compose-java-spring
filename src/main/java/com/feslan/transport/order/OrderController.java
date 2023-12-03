package com.feslan.transport.order;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }
    @GetMapping("/{id}")
    public Order getUserById(@PathVariable Long id){
        return orderRepository.findById(id).get();
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return orderRepository.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order){
        Order existingOrder = orderRepository.findById(id).get();
        existingOrder.setDestination(order.getDestination());
        existingOrder.setDriver(order.getDriver());
        existingOrder.setTarget(order.getTarget());
        return  orderRepository.save(existingOrder);

    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id){
        try {
            orderRepository.findById(id);
            orderRepository.deleteById(id);
            return "User deleted successfully";

        } catch (Exception e){
            return "User not found";
        }
    }

}
