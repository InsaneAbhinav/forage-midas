package com.jpmc.midascore.service;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IncentiveClient {

    private final RestTemplate restTemplate;

    public IncentiveClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Incentive fetchIncentive(Transaction transaction) {
        return restTemplate.postForObject(
                "http://localhost:8080/incentive",
                transaction,
                Incentive.class
        );
    }
}
