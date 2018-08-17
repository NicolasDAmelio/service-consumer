package com.ndamelio.learning.serviceconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NumberAdderController {

    private Log log = LogFactory.getLog(NumberAdderController.class);

    private RandomServiceProxy randomServiceProxy;

    public NumberAdderController(RandomServiceProxy randomServiceProxy) {
        this.randomServiceProxy = randomServiceProxy;
    }

    @HystrixCommand(fallbackMethod = "getDefaultResponse")
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
        log.warn("Returning "+ sum);
        return sum;
    }

    public Long getDefaultResponse() {
        return 10000L;
    }

}
