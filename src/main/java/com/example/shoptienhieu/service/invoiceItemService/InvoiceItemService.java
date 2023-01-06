package com.example.shoptienhieu.service.invoiceItemService;

import com.example.shoptienhieu.entities.InvoiceItem;
import com.example.shoptienhieu.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InvoiceItemService {
    public List<InvoiceItem> getAll();
    public InvoiceItem getById(int id);
    public void deleteById(int id);
    public List<InvoiceItem> getByInvoiceId(String invoiceId);
    public List<InvoiceItem> getByProductId(String productId);
}
