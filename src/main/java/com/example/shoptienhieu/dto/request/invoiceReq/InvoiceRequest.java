package com.example.shoptienhieu.dto.request.invoiceReq;

import lombok.Data;

@Data
public class InvoiceRequest {
    private String invoiceId;
    private int userId;
    private int deliveryId;
    private int paymentId;
    private int shopId;
    private String status;
    private String shippingAddress;
    private int shippingFee;
    private String receiverPhone;
    private String receiverName;
    private int paidAmount;
}
