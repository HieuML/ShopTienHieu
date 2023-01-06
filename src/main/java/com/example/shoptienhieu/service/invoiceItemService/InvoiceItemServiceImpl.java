package com.example.shoptienhieu.service.invoiceItemService;

import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.entities.Invoice;
import com.example.shoptienhieu.entities.InvoiceItem;
import com.example.shoptienhieu.entities.Product;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.repository.InvoiceItemRepository;
import com.example.shoptienhieu.repository.InvoiceRepository;
import com.example.shoptienhieu.repository.ProductRepository;
import com.example.shoptienhieu.service.invoiceService.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceItemServiceImpl implements InvoiceItemService {
    @Autowired
    InvoiceItemRepository invoiceItemRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceItem> getAll() {
        return invoiceItemRepository.findAll();
    }

    @Override
    public InvoiceItem getById(int id) {
        return invoiceItemRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(TextStatus.INVOICE_ITEM_NOT_FOUND)
        );
    }

    @Override
    public void deleteById(int id) {
        InvoiceItem invoiceItem = getById(id);
        invoiceItemRepository.delete(invoiceItem);
    }

    @Override
    public List<InvoiceItem> getByInvoiceId(String invoiceId) {
        return invoiceItemRepository.findByInvoiceId(invoiceId);
    }

    @Override
    public List<InvoiceItem> getByProductId(String productId) {
        return invoiceItemRepository.findByProductId(productId);
    }
}
