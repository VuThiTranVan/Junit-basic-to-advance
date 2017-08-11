package com.tamnt.mockito.examples.service.dao.impl;

import com.tamnt.mockito.examples.service.dao.AbstractDAO;
import com.tamnt.mockito.examples.service.dao.CoinDetailDAO;
import com.tamnt.mockito.examples.service.entity.CoinDetail;
import org.springframework.stereotype.Repository;


@Repository
public class CoinDetailDAOImpl extends AbstractDAO implements CoinDetailDAO {

    @Override
    public CoinDetail create(CoinDetail coinDetail) {
        return (CoinDetail) super.create(coinDetail);
//        return coinDetail;
    }

    @Override
    public void updateCoinDetail(CoinDetail coinDetail) {
        super.update(coinDetail);
    }

}