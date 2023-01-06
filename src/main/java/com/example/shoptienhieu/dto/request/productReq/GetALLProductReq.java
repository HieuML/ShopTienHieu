package com.example.shoptienhieu.dto.request.productReq;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetALLProductReq {
    private Integer userID;
    private String sort;
    private String productName;
    private Integer limit;
    private Integer pageIndex;

}
