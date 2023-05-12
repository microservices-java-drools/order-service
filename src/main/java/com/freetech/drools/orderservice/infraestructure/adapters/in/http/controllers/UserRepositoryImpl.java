package com.freetech.drools.orderservice.infraestructure.adapters.in.http.controllers;

import org.springframework.stereotype.Component;
import pe.gob.contraloria.sisco.models.UserRepository;

@Component
public class UserRepositoryImpl implements UserRepository{
    @Override
    public void createUser() {
        System.out.println("Creating user");
    }
}
