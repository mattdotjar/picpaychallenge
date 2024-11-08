package com.picpaychallenge.picpay_challenge.repositories;

import com.picpaychallenge.picpay_challenge.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    



}
