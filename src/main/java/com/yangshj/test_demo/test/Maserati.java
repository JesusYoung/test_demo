package com.yangshj.test_demo.test;

import org.springframework.stereotype.Service;

@Service
public class Maserati implements ICarService {

    @Override
    public String findCar() {
        return "This is a Maserati";
    }
}
