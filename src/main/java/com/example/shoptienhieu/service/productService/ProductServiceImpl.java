package com.example.shoptienhieu.service.productService;

import com.example.shoptienhieu.constants.Sort;
import com.example.shoptienhieu.constants.TextStatus;
import com.example.shoptienhieu.dto.request.productReq.CreateOrUpdateProductReq;
import com.example.shoptienhieu.dto.request.productReq.GetALLProductReq;
import com.example.shoptienhieu.dto.response.productRes.ProductRes;
import com.example.shoptienhieu.entities.*;
import com.example.shoptienhieu.exception.ResourceNotFoundException;
import com.example.shoptienhieu.exception.SearchByNameException;
import com.example.shoptienhieu.repository.*;
import com.example.shoptienhieu.utils.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private OriginRepository originRepository;
    @Autowired
    private ShopRepository shopRepository;




    @Override
    public Map<String, Object> getAllProduct(GetALLProductReq getALLProductReq) {
        String productName = getALLProductReq.getProductName();
        Integer pageIndex = getALLProductReq.getPageIndex();
        Integer limit = getALLProductReq.getLimit();
        String  sort = getALLProductReq.getSort();
        String sortTmp = "";
        Integer userID = getALLProductReq.getUserID();

        Shop shop =  shopRepository.findByUserId(userID);
        Integer shopID =  shopRepository.findByUserId(userID).getId();

        String[] sortArr;
        if(sort.equals("DEFAULT")||sort.equals("CREATED_ASC")||sort.equals("CREATED_DESC")||
                sort.equals("PRICE_ASC")||sort.equals("PRICE_DESC"))
        {
            switch (sort) {
                case "DEFAULT":
                    sortTmp = Sort.DEFAULT;
                    break;
                case "CREATED_ASC" :
                    sortTmp = Sort.CREATED_ASC;
                    break;
                case "CREATED_DESC" :
                    sortTmp = Sort.CREATED_DESC;
                    break;
                case "PRICE_ASC" :
                    sortTmp = Sort.PRICE_ASC;
                    break;
                case "PRICE_DESC":
                    sortTmp = Sort.PRICE_DESC;
                    break;
            }
            sortArr = sortTmp.split(",");
        }
        else {
            throw new ResourceNotFoundException(TextStatus.ORDER_NOT_FOUND);
        }
        if(sortArr[0].equals("id")){
            sortArr[0] = "productID";
        }
        org.springframework.data.domain.Sort.Order order = new org.springframework.data.domain.Sort.Order(CommonMethod.findDirection(sortArr[1]), sortArr[0]);

        Pageable pagingSort = PageRequest.of(pageIndex,limit, org.springframework.data.domain.Sort.by(order));

        Page<Product> pageProds;

        if(productName != null) {
            productName = productName.trim();
            if (productName.equals("")) {
                throw new SearchByNameException(TextStatus.SEARCH_BY_NAME_ERROR);
            }
            pageProds = productRepository.findByShopIdAndNameContaining(productName,shop,pagingSort);
        }
        else {
            pageProds = productRepository.findByShopId(shopID,pagingSort);
        }


        List<ProductRes> productReses = new ArrayList<>();

        for(Product product: pageProds.getContent()) {
            productReses.add(new ProductRes(product));
        }
        if (productReses.isEmpty()) {
            throw new ResourceNotFoundException(TextStatus.FIND_PRODUCT_BY_SHOPID_ERROR);
        }


        Map<String, Object> response = new HashMap<>();
        response.put("totalItem",pageProds.getTotalElements());
        response.put("listProduct",productReses);
        response.put("totalPage",pageProds.getTotalPages());
        response.put("pageSize",limit);
        response.put("pageIndex",pageIndex);
        response.put("hasNextPage", (pageIndex == pageProds.getTotalPages() - 1) ? false : true);

//        PageRes<ProductRes> pageRes = new PageRes<>(response.get("totalItem"),response.get("totalPage"),
//                response.get("pageSize"),response.get("pageIndex"),response.get("hasNextPage"));


        return response;
    }

    @Override
    public ProductRes getDetailProduct(String productID) {
        Optional<Product> productData = productRepository.findById(productID);
        ProductRes productRes = null;
        if (productData.isPresent()) {
            productRes = new ProductRes(productData.get());
        } else {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
        return productRes;
    }

    @Override
    public void createProduct(CreateOrUpdateProductReq product) {
        checkProduct(product);
        System.out.println("ok");
        productRepository.save(
                new Product(product.getName(), product.getDescription(), product.getPrice(),
                        product.getOriginPrice(), brandRepository.findById(product.getBrandId()).get(),
                        categoryRepository.findById(product.getCategoryId()).get(),
                        shopRepository.findById(product.getShopId()).get(),
                        originRepository.findById(product.getOriginId()).get(), product.getInStock(), product.getSlug()
                ));
        System.out.println("end");
    }

    @Override
    public void updateProduct(CreateOrUpdateProductReq product) {
        checkProduct(product);
        Optional<Product> productData = productRepository.findById(product.getProductId());
        if (productData.isPresent()) {
            Product product_ = productData.get();
            product_.setName(product.getName());
            product_.setDescription(product.getDescription());
            product_.setPrice(product.getPrice());
            product_.setOriginPrice(product.getOriginPrice());
            product_.setBrand(brandRepository.findById(product.getBrandId()).get());
            product_.setCategory(categoryRepository.findById(product.getCategoryId()).get());
            product_.setShop(shopRepository.findById(product.getShopId()).get());
            product_.setOrigin(originRepository.findById(product.getOriginId()).get());
            product_.setInStock(product_.getInStock());
            product_.setSlug(product_.getSlug());
            product_.setUpdatedAt(new Date().getTime());
            productRepository.save(product_);
        } else {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void deleteProduct(String productID) {
        Optional<Product> productData = productRepository.findById(productID);
        if (productData.isPresent()) {
            productRepository.deleteById(productID);
        } else {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public void changeStatus(String productID) {
        Optional<Product> productData = productRepository.findById(productID);
        if (productData.isPresent()) {
            Product product = productData.get();
            product.setIsActive(true);
            productRepository.save(product);
        } else {
            throw new ResourceNotFoundException(TextStatus.PRODUCT_NOT_FOUND);
        }
    }

    public void checkProduct(CreateOrUpdateProductReq product) {
        Optional<Brand> brandData = brandRepository.findById(product.getBrandId());
        if (!brandData.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.BRAND_NOT_FOUND);
        }
        Optional<Category> categoryData = categoryRepository.findById(product.getCategoryId());
        if (!categoryData.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.CATEGORY_NOT_FOUND);
        }
        Optional<Origin> originData = originRepository.findById(product.getOriginId());
        if (!originData.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.ORIGIN_NOT_FOUND);
        }
        Optional<Shop> shopData = shopRepository.findById(product.getShopId());
        if (!shopData.isPresent()) {
            throw new ResourceNotFoundException(TextStatus.SHOP_NOT_FOUND);
        }
    }
}
