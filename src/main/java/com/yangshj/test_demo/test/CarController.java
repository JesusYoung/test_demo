package com.yangshj.test_demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/car")
public class CarController {

//    @Resource()
//    @Qualifier("lamborghini")
    @Autowired
    private ICarService carService;


    @GetMapping("/find")
    public String getCar() {
        return this.carService.findCar();
    }
}
