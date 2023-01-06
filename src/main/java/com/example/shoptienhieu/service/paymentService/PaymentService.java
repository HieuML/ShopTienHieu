package com.example.shoptienhieu.service.paymentService;

import com.example.shoptienhieu.dto.request.paymentReq.PaymentReq;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Payment;

import java.util.List;

public interface PaymentService {
    public List<IdNameRes> getAll();
    public Payment getById(int id);
    public Payment create(PaymentReq paymentReq);
    public Payment updateById(PaymentReq paymentReq);
    public void deleteById(int id);
}
