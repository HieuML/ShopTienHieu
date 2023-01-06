package com.example.shoptienhieu.service.walletService;

import com.example.shoptienhieu.dto.request.walletReq.depositOrWithdrawReq;
import com.example.shoptienhieu.dto.response.BaseRes;
import com.example.shoptienhieu.dto.response.walletRespone.DetailWalletRes;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    public BaseRes<DetailWalletRes> getByUserId(Integer UserId);

    public BaseRes<Integer> withdraw (depositOrWithdrawReq request);

    public BaseRes<Integer> deposit (depositOrWithdrawReq request);



}
