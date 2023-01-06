package com.example.shoptienhieu.constants;

public interface TextStatus {

    // Success
    String REGISTER_SUCCESS = "Success: User registered successfully!";

    String LOGIN_SUCCESS = "Success: User login successfully ";

    String REFRESH_TOKEN_SUCCESS = "Success: refresh token successfully";

    String LOGOUT_SUCCESS = "Success: Log out successful!";

    String GET_INFO_SUCCESS = "Success: Get info successfully";

    // User

    String CHANGE_PASSWORD_SUCCESS = "Success: change password successfully";

    String CHANGE_PASSWORD_ERROR = "Error: Password incorrect";




    // Success wallet

    String GET_ALL_WALLET_SUCCESS = "Success: Get all wallet successfully";

    String GET_BY_WALLET_ID_SUCCESS = "Success: Get by wallet id successfully";

    String GET_DETAIL_WALLET_SUCCESS = "Success: Get detail wallet by user's id successfully";

    String WITHDRAW_SUCCESS = "Success: withdraw successfully";

    String DEPOSIT_SUCCESS = "Success: deposit successfully";


    // Error wallet
    String WITHDRAW_ERROR = "Success: money in wallet not enough!";


    // Error
    String BRAND_NOT_FOUND = "Error: Brand not found!";
    String DELIVERY_NOT_FOUND = "Error: Delivery not found!";
    String CATEGORY_NOT_FOUND = "Error: Category not found!";
    String SHOP_NOT_FOUND = "Error: Shop not found!";
    String ORIGIN_NOT_FOUND = "Error: Origin not found!";
    String WARD_NOT_FOUND = "Error: Ward not found!";

    String ROLE_NOT_FOUND = "Error: Role not found!";

    //Product

    String UPDATE_PRODUCT_SUCCESS = "Success: Update product successfully";
    String CREATE_PRODUCT_SUCCESS = "Success: Create product successfully";
    String GET_ALL_PRODUCT_SUCCESS = "Success: Get all product successfully";
    String GET_DETAIL_PRODUCT_SUCCESS = "Success: Get detail product successfully";
    String CHANGE_STATUS_PRODUCT_SUCCESS = "Success: Get detail product successfully";
    String DELETE_PRODUCT_SUCCESS = "Success: Get detail product successfully";

    String FIND_PRODUCT_BY_SHOPID_SUCCESS = "Success: Get all product success";
    String UPDATE_PRODUCT_ERROR = "Error: ProductID not found!";


    String SEARCH_BY_NAME_ERROR = "Error: Product name invalid";

    String FIND_PRODUCT_BY_SHOPID_ERROR = "Success: Get all product by shopID successfully";
    //Invoice
    String CREATE_INVOICE_SUCCESS = "Success: Create invoice successfully";
    String UPDATE_INVOICE_SUCCESS = "Success: Update invoice successfully";
    String GET_ALL_INVOICE_SUCCESS = "Success: Get All invoice successfully";

    String DELETE_INVOICE_SUCCESS = "Success: Delete invoice successfully";
    // Auth

    String USERNAME_EXIST = "Error: Username is already taken!";

    String EMAIL_EXIST = "Error: Email is already in use!";

    String ACCOUNT_LOCKED = "Error: Account is locked";

    String LOGIN_ERROR = "Bad credentials";

    String REFRESH_TOKEN_NOT_FOUND = "Error: Refresh token is not in database!";


    String REFRESH_TOKEN_EXPIRED = "Error: Refresh token was expired. Please make a new login request";

    String INVALID_ACCESS_TOKEN = "Error: Invalid access token";

    String LOGOUT_ERROR = "Error: UserId not exist";
    String PERMISSION_NOT_FOUND = "Permission not found";
    String CITY_NOT_FOUND = "City nos found";
    String PRODUCT_IMAGE_NOT_FOUND = "Product image not found";
    String SHIPPING_ADDRESS_NOT_FOUND = "Shipping not found";
    String PRODUCT_NOT_FOUND = "Product not found";
    String WALLET_NOT_FOUND = "Wallet not found!";
    String ORDER_NOT_FOUND = "Order not found!";
    String USER_NOT_FOUND = "User not found!";
    String PAYMENT_NOT_FOUND = "Payment not found!";
    String INVOICE_NOT_FOUND = "Invoice nto found";
    String INVOICE_ITEM_NOT_FOUND = "Invoice item not found!";
    String BUY_TYPE_NOT_FOUND = "BuyType not found!";


    String SHOPPING_CART_NOT_FOUND = "Shopping cart not found";
    String PRODUCT_COMMENT_NOT_FOUND = "Product comment not found";
    String CART_ITEM_NOT_FOUND = "Cart Item not found";
}
