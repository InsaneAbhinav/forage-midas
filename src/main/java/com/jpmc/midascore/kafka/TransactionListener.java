package com.jpmc.midascore.kafka;

import com.jpmc.midascore.foundation.Incentive;
import com.jpmc.midascore.foundation.Transaction;
import com.jpmc.midascore.repository.TransactionRepository;
import com.jpmc.midascore.service.IncentiveClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransactionListener {

    private final TransactionRepository repository;
    private final IncentiveClient incentiveClient;

    public TransactionListener(TransactionRepository repository,
                               IncentiveClient incentiveClient) {
        this.repository = repository;
        this.incentiveClient = incentiveClient;
    }

    @KafkaListener(topics = "${general.kafka-topic}")
    public void listen(Transaction transaction) {
        Incentive incentive = incentiveClient.fetchIncentive(transaction);
        transaction.setIncentive(incentive.getAmount());
        repository.save(transaction);
    }
}
