package com.example.shoptienhieu.dto.request.shippingAddressReq;

import lombok.Data;

@Data
public class ShippingAddressRequest {
    private int userId;
    private int cityId;
    private boolean type;
    private boolean isDefault;
    private String addressDetail;
    private String phone;
}
