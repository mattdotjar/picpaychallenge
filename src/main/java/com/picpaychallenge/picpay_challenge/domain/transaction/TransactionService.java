package com.picpaychallenge.picpay_challenge.domain.transaction;


import com.picpaychallenge.picpay_challenge.domain.user.User;
import com.picpaychallenge.picpay_challenge.dtos.TransactionDTO;
import com.picpaychallenge.picpay_challenge.repositories.TransactionRepository;
import com.picpaychallenge.picpay_challenge.services.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TransactionService {


    @Autowired
    private UserService userService;

    @Autowired
    TransactionRepository repo;


    @Autowired
    private RestTemplate restTemplate;


    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = this.userService.findUserById(transaction.receiverId());
        User receiver = this.userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());


        boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
        if (!isAuthorized){
            throw new Exception("Transação não autorizada");
        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(newTransaction.getAmount());
        newTransaction.setSender(sender);
        newTransaction.setReceiver(receiver);
        newTransaction.setTimestamp(LocalDateTime.from(LocalDate.now()));


        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repo.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }


    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity(("https://util.devi.tools/api/v2/authorize"), Map.class);

        if (authorizationResponse.getStatusCode() = HttpStatus.OK) {

            String message = (String) authorizationResponse.getBody().get("message");
            return "Autorizado".equalsIgnoreCase(message);
        } else return false;


    }


}
