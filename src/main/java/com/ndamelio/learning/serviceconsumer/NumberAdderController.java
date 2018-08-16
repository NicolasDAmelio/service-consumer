package com.ndamelio.learning.serviceconsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NumberAdderController {

    private static final Logger LOG = LoggerFactory.getLogger(NumberAdderController.class);

    @Value("${number.service.url}")
    private String numberServiceUrl;

    @RequestMapping("/add")
    public Long add() {
        long sum = 0;

        ResponseEntity<Integer[]> responseEntity = new RestTemplate()
                .getForEntity(numberServiceUrl, Integer[].class);

        Integer[] numbers = responseEntity.getBody();
        for (int number: numbers) {
            sum += number;
        }
        LOG.warn("Returning {}", sum);
        return sum;
    }

}
