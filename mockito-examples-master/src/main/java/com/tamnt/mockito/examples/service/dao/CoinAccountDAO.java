package com.tamnt.mockito.examples.service.dao;


import com.tamnt.mockito.examples.service.entity.CoinAccount;

public interface CoinAccountDAO {

    CoinAccount addCoinAccount(CoinAccount coinAccount);

    void createCoinAccount(CoinAccount coinAccount);

    CoinAccount updateCoinAccount(CoinAccount coinAccount);

}
