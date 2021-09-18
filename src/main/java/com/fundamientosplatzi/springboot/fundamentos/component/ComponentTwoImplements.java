package com.fundamientosplatzi.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplements implements ComponentDependency{
    @Override
    public void saludar() {
        System.out.println("hola mundo desde mi segundo componente");
    }
}
