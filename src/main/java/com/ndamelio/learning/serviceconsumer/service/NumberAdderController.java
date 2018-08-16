package com.ndamelio.learning.serviceconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NumberAdderController {

    private static final Logger LOG = LoggerFactory.getLogger(NumberAdderController.class);

    private RandomServiceProxy randomServiceProxy;

    public NumberAdderController(RandomServiceProxy randomServiceProxy) {
        this.randomServiceProxy = randomServiceProxy;
    }

    @RequestMapping("/add")
    public Long add() {
        long sum = 0;

        List<Integer> numbers = randomServiceProxy.getRandomNumbers();

//        ResponseEntity<Integer[]> responseEntity = new RestTemplate()
//                .getForEntity(numberServiceUrl, Integer[].class);
//
//        Integer[] numbers = responseEntity.getBody();
        for (int number: numbers) {
            sum += number;
        }
        LOG.warn("Returning {}", sum);
        return sum;
    }

}
