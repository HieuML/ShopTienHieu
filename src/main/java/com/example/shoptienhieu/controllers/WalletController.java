package com.example.shoptienhieu.controllers;

import com.example.shoptienhieu.constants.Path;
import com.example.shoptienhieu.dto.request.walletReq.depositOrWithdrawReq;
import com.example.shoptienhieu.service.walletService.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/wallet")
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping(Path.DETAIL)
    public ResponseEntity<?> getByUserId(@PathVariable Integer userID) {

        return  ResponseEntity.ok(walletService.getByUserId(userID)) ;
    }

    @PostMapping(Path.WITHDRAW)
    public ResponseEntity<?> withdraw(@RequestBody depositOrWithdrawReq request) {

        return  ResponseEntity.ok(walletService.withdraw(request)) ;
    }

    @PostMapping(Path.DEPOSIT)
    public ResponseEntity<?> deposit(@RequestBody depositOrWithdrawReq request) {

        return  ResponseEntity.ok(walletService.deposit(request)) ;
    }

}
