package com.picpaychallenge.picpay_challenge.services;


import com.picpaychallenge.picpay_challenge.domain.user.User;
import com.picpaychallenge.picpay_challenge.domain.user.UserType;
import com.picpaychallenge.picpay_challenge.dtos.UserDTO;
import com.picpaychallenge.picpay_challenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{

        if (sender.getUserType() == UserType.MERCHANT) {
            throw new Exception("Usuario do tipo MERCHANT não possa realizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente!");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repo.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
    }

    public void saveUser(User user) {
        this.repo.save(user);
    }

    public User createUser(UserDTO data) {
        User newuser = new User(data);
        this.saveUser(newuser);
        return newuser;
    }

    public List<User> getAllUsers(){
        return this.repo.findAll();
    }


}
