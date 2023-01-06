package com.example.shoptienhieu.repository;

import com.example.shoptienhieu.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    public List<Invoice> findByUserId(int userId);
    public List<Invoice> findByPaymentId( int paymentId);
    public List<Invoice> findByDeliveryId(int deliveryId);
    public List<Invoice> findByShopId(int shopId);

    //    Page<Invoice> findAllByUser (Pageable pageable);
//   Page<Invoice> findALl (Pageable pageable);
//    List<Invoice> findAllByUser(int userId, Pageable pageable);
}
