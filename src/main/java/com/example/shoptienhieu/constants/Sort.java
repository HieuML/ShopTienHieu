package com.example.shoptienhieu.constants;

public interface Sort {
    // invoice
    String INVOICE_ID = "invoiceID";
    String INVOICE_SHOP = "shop";
    String INVOICE_USER = "user";

    String DEFAULT = "id,asc";
    String PRICE_ASC = "price,asc";
    String PRICE_DESC = "price,desc";
    String CREATED_ASC = "createdAt,asc";
    String CREATED_DESC = "createdAt,desc";
}
