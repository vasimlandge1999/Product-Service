package com.product.service;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
