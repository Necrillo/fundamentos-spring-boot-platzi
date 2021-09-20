package com.fundamientosplatzi.springboot.fundamentos.bean;

public class My2BeanImplement implements MyBean{
    @Override
    public void print() {
        System.out.println("hola desde mi mplementacion propia del bean 2");
    }
}
