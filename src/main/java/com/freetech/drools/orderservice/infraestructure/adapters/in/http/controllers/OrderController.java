package com.freetech.drools.orderservice.infraestructure.adapters.in.http.controllers;

//import com.freetech.drools.orderservice.domain.Order;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.contraloria.sisco.models.Order;

@RequiredArgsConstructor
@RequestMapping(value = "v1/orders")
@RestController
public class OrderController {
    private final KieSession session;

    @PostMapping("/")
    public Order createOrder(@RequestBody Order order) {
        session.insert(order);
        session.fireAllRules();
        return order;
    }
}
