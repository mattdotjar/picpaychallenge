package com.picpaychallenge.picpay_challenge.domain.transaction;


import com.picpaychallenge.picpay_challenge.dtos.TransactionDTO;
import com.picpaychallenge.picpay_challenge.repositories.TransactionRepository;
import com.picpaychallenge.picpay_challenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {


    @Autowired
    private UserService service;

    @Autowired
    TransactionRepository repo;


    public void createTransaction(TransactionDTO transaction){

    }


}
