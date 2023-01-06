package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.InvoiceItem;
import com.example.shoptienhieu.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Integer> {
    public List<InvoiceItem> findByProductId(String productId);
    public List<InvoiceItem> findByInvoiceId(String invoiceId);
}
