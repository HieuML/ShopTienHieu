package com.example.shoptienhieu.service.paymentService;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.paymentReq.PaymentReq;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.entities.Origin;
import com.example.shoptienhieu.entities.Payment;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.dnd.DropTargetEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentRepository paymentRepository;
    @Override
    public List<IdNameRes> getAll() {
        List<Payment> payments =  paymentRepository.findAll();
        List<IdNameRes> list = new ArrayList<>();
        payments.stream().forEach(
                o->{
                    IdNameRes idNameRes = new IdNameRes(o.getId(), o.getName());
                    list.add(idNameRes);
                }
        );
        return list;
    }

    @Override
    public Payment getById(int id) {
        return paymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.PAYMENT_NOT_FOUND)
        );
    }

    @Override
    public Payment create(PaymentReq paymentReq) {
        return paymentRepository.save(new Payment(paymentReq.getName()));
    }

    @Override
    public Payment updateById(PaymentReq paymentReq) {
        Payment payment = getById(paymentReq.getId());
        payment.setName(paymentReq.getName());
        payment.setUpdatedAt(new Date().getTime());
        return paymentRepository.save(payment);
    }

    @Override
    public void deleteById(int id) {
        Payment payment = getById(id);
        paymentRepository.delete(payment);
    }
}
