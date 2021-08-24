package com.yangshj.test_demo.test;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class Lamborghini implements ICarService {

    @Override
    public String findCar() {
        return "This is a Lamborghini";
    }
}
