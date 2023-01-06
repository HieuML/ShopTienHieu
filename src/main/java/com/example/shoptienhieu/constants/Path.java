package com.example.shoptienhieu.constants;

public interface Path {

    // Crud
    String GET_ALL = "/get-all";
    String CREATE = "/create";
    String UPDATE = "/update";
    String DELETE = "/delete";

    // Auth
    String LOGIN = "/login";
    String REGISTER = "/register";
    String INFO = "/info";
    String LOGOUT = "/logout";
    String REFRESH_TOKEN = "/refresh-token";
    String SENDING_OTP = "/sending-otp";
    String CONFIRM_OTP = "/confirm-otp";
    String UPDATE_NEW_PASSWORD = "/update-password";

    String CHANGE_PASSWORD = "/change-password";

    // Wallet
    String DETAIL = "/detail{userID}";
    String WITHDRAW = "/withdraw";
    String DEPOSIT = "/deposit";
    // Product
    String CHANGE_STATUS = "/change-status";
    String GET_INVOICE_BY_USER = "get-by-user";
    String GET_INVOICE_BY_SHOP = "get-by-shop/{shopId}";
    String GET_INVOICE_BY_DELIVERY = "get-by-delivery/{deliveryId}";
    String GET_INVOICE_BY_PAYMENT = "get-by-payment/{paymentId}";

    // Shop

    String GET_DETAIL_SHOP = "/detail/{userID}";

}
