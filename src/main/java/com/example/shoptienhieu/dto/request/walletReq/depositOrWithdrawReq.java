package com.example.shoptienhieu.dto.request.walletReq;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class depositOrWithdrawReq {
    private Integer userId;
    private Integer money;
}
