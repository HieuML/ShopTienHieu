package com.example.shoptienhieu.service.productService;

import com.example.shoptienhieu.dto.request.productReq.CreateOrUpdateProductReq;
import com.example.shoptienhieu.dto.request.productReq.GetALLProductReq;
import com.example.shoptienhieu.dto.response.BaseResCollect;
import com.example.shoptienhieu.dto.response.IdNameRes;
import com.example.shoptienhieu.dto.response.productRes.ProductRes;

import java.util.Map;


public interface ProductService {
//    /**
//     * get all products and paging
//     * @return
//     */
//    Page<ProductSearchDTO> getAllProducts(Pageable pageable);
//
//    /**
//     * find products by productName
//     * @param productName
//     * @return
//     */
//    List<Product> findByName(String productName);
//
//    /**
//     * get product detail by productId
//     * @param productId
//     * @return
//     */
//    Product getById(int productId);
//
//    /**
//     * find product by information searching
//     * @param infoSearchRequest
//     * @return
//     */
//    Page<ProductSearchDTO> findByInfoSearch(InfoSearchRequest infoSearchRequest, Pageable pageable);


    Map<String, Object> getAllProduct(GetALLProductReq getALLProductReq);

    ProductRes getDetailProduct(String productID);

    void createProduct(CreateOrUpdateProductReq product);
    void updateProduct(CreateOrUpdateProductReq product);
    void deleteProduct(String productID);
    void changeStatus(String productID);
}
