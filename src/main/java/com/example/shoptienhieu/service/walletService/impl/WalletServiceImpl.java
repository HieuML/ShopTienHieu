package com.example.shoptienhieu.service.walletService.impl;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.entities.Wallet;
import com.example.shoptienhieu.repository.WalletRepository;
import com.example.shoptienhieu.dto.request.walletReq.depositOrWithdrawReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.walletRespone.DetailWalletRes;
import com.example.shoptienhieu.service.walletService.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;

    @Override
    public BaseRes<DetailWalletRes> getByUserId(Integer UserId) {
        return new BaseRes<>(HttpStatus.OK.value(), TextStatus.GET_DETAIL_WALLET_SUCCESS,new DetailWalletRes(walletRepository.findByUserId(UserId).getMoney())
                );
    }

    @Override
    public BaseRes<Integer> withdraw(depositOrWithdrawReq request) {

        Wallet wallet =  walletRepository.findByUserId(request.getUserId());

        Integer moneyCurrent = wallet.getMoney();
        if( moneyCurrent< request.getMoney()) {
            return new BaseRes<>(HttpStatus.OK.value(), TextStatus.WITHDRAW_ERROR, moneyCurrent);
        }
        else {
            wallet.withdraw(request.getMoney());
            walletRepository.save(wallet);
            return new BaseRes<>(HttpStatus.OK.value(), TextStatus.WITHDRAW_SUCCESS, wallet.getMoney()
                    );
        }
    }

    @Override
    public BaseRes<Integer> deposit(depositOrWithdrawReq request) {

        Wallet wallet =  walletRepository.findByUserId(request.getUserId());
        wallet.deposit(request.getMoney());
        walletRepository.save(wallet);
    return new BaseRes<>(HttpStatus.OK.value(), TextStatus.DEPOSIT_SUCCESS, wallet.getMoney());

    }

}
