package com.example.shoptienhieu.service.invoiceService.impl;

import com.example.shoptienhieu.constants.Sort;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.invoiceReq.InvoiceRequest;
import com.example.shoptienhieu.entities.*;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.*;
import com.example.shoptienhieu.service.invoiceService.InvoiceService;
import com.example.shoptienhieu.service.productService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    ProductService productService;
    @Autowired
    DeliveryRepository deliveryRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    @Override
    public Page<Invoice> getAll(int sortBy, String sort, int page, int limit) {
//        List<Invoice> list = new ArrayList<>();
        org.springframework.data.domain.Sort sort1 = null;
        String sortBy1 = null;
        if (sortBy == 1){
            sortBy1 = Sort.INVOICE_SHOP;
        }else if (sortBy == 2){
            sortBy1 = Sort.INVOICE_USER;
        }else{
            sortBy1 = Sort.INVOICE_ID;
        }

        if(sort.equals("asc")){
            sort1 = org.springframework.data.domain.Sort.by(sortBy1).ascending();
        }else{
            sort1 = org.springframework.data.domain.Sort.by(sortBy1).descending();
        }
        Pageable pageable = PageRequest.of(page ,limit, sort1);
//        list = invoiceRepository.findAll(pageable).getContent();
        return invoiceRepository.findAll(pageable);
    }

    @Override
    public Invoice getById(String id) {
        return invoiceRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(TextStatus.INVOICE_NOT_FOUND)
        );
    }

    @Override
    public List<Invoice> getByDeliveryId(int deliveryId, String sort, int page, int limit) {
        Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
        if(!delivery.isPresent()){
            throw new ResourceNotFoundException(TextStatus.DELIVERY_NOT_FOUND);
        }
//        List<Invoice> list = invoiceRepository.findByDeliveryId()
        return invoiceRepository.findByDeliveryId(deliveryId);
    }

    @Override
    public List<Invoice> getByShopId(int shopId, String sort, int page, int limit) {
        Optional<Shop> shop = shopRepository.findById(shopId);
        if(!shop.isPresent()){
            throw new ResourceNotFoundException(TextStatus.SHOP_NOT_FOUND);
        }
        return invoiceRepository.findByShopId(shopId);
    }

    @Override
    public List<Invoice> getByPaymentId(int paymentId, String sort, int page, int limit) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if(!payment.isPresent()){
            throw new ResourceNotFoundException(TextStatus.PAYMENT_NOT_FOUND);
        }
        return invoiceRepository.findByPaymentId(paymentId);
    }

    @Override
    public List<Invoice> getByUserId(int userId, String sort, int page, int limit) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.USER_NOT_FOUND);
        }
        return invoiceRepository.findByUserId(userId);
    }

    @Override
    public void create(InvoiceRequest invoiceRequest) {
        checkInvoice(invoiceRequest);
        Invoice invoice = new Invoice(userRepository.findById(invoiceRequest.getUserId()).get()
                , deliveryRepository.findById(invoiceRequest.getDeliveryId()).get()
                , paymentRepository.findById(invoiceRequest.getPaymentId()).get()
                , shopRepository.findById(invoiceRequest.getShopId()).get()
        , invoiceRequest.getStatus(), invoiceRequest.getShippingAddress(),invoiceRequest.getShippingFee()
                ,invoiceRequest.getReceiverPhone(), invoiceRequest.getReceiverName());
        invoice.setTotalAmount(getTotalAmount(invoice.getId()));
        invoiceRepository.save(invoice);
    }

    @Override
    public void updateById(InvoiceRequest invoiceRequest) {
        checkInvoice(invoiceRequest);
        Invoice invoice = getById(invoiceRequest.getInvoiceId());
        invoice.setDelivery(deliveryRepository.findById(invoiceRequest.getDeliveryId()).get());
        invoice.setPayment(paymentRepository.findById(invoiceRequest.getPaymentId()).get());
        invoice.setPaidAmount(invoiceRequest.getPaidAmount());
        invoice.setShop(shopRepository.findById(invoiceRequest.getShopId()).get());
        invoice.setUpdatedAt(new Date().getTime());
        invoice.setStatus(invoiceRequest.getStatus());
        invoice.setUser(userRepository.findById(invoiceRequest.getUserId()).get());
        invoice.setReceiverName(invoiceRequest.getReceiverName());
        invoice.setReceiverPhone(invoiceRequest.getReceiverPhone());
        invoice.setShippingFee(invoiceRequest.getShippingFee());
        invoice.setTotalAmount(getTotalAmount(invoiceRequest.getInvoiceId()));
        invoice.setShippingAddress(invoiceRequest.getShippingAddress());
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteById(String id) {
        Invoice invoice = getById(id);
        invoiceRepository.delete(invoice);

    }

    @Override
    public void checkInvoice(InvoiceRequest invoiceRequest) {
        Optional<User> user = userRepository.findById(invoiceRequest.getUserId());
        if (!user.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.USER_NOT_FOUND);
        }
        Optional<Shop> shop = shopRepository.findById(invoiceRequest.getShopId());
        if (!shop.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.SHOP_NOT_FOUND);
        }
        Optional<Delivery> delivery = deliveryRepository.findById(invoiceRequest.getDeliveryId());
        if (!delivery.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.DELIVERY_NOT_FOUND);
        }
        Optional<Payment> payment = paymentRepository.findById(invoiceRequest.getPaymentId());
        if (!payment.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.PAYMENT_NOT_FOUND);
        }
    }
    private Pageable getPageable(String sort, int page, int limit){
        org.springframework.data.domain.Sort sort1 = null;
        if(sort.equals("asc")){
            sort1 = org.springframework.data.domain.Sort.by(Sort.INVOICE_ID).ascending();
        }else{
            sort1 = org.springframework.data.domain.Sort.by(Sort.INVOICE_ID).descending();
        }
        Pageable pageable = PageRequest.of(page, limit, sort1);
        return pageable;
    }

    private int getTotalAmount(String invoiceId) {
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByInvoiceId(invoiceId);
        int totalPrice = 0;
        for (InvoiceItem invoiceItem : invoiceItems) {
            totalPrice += invoiceItem.getProduct().getPrice();
        }
        return totalPrice;
    }
}
