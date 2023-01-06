package com.example.shoptienhieu.service.invoiceService;

import com.example.shoptienhieu.dto.request.invoiceReq.InvoiceRequest;
import com.example.shoptienhieu.entities.Invoice;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {
    public List<Invoice> getAll();
    public Page<Invoice> getAll(int sortBy, String sort, int page, int limit);
    public Invoice getById(String id);
    public List<Invoice> getByDeliveryId(int deliveryId, String sort, int page, int limit);
    public List<Invoice> getByShopId(int shopId, String sort, int page, int limit);
    public List<Invoice> getByPaymentId(int paymentId,String sort, int page, int limit);
    public List<Invoice> getByUserId(int userId, String sort, int page, int limit);
    public void create(InvoiceRequest invoiceRequest);
    public void updateById(InvoiceRequest invoiceRequest);
    public void deleteById(String id);
    public void checkInvoice(InvoiceRequest invoiceRequest);
}
