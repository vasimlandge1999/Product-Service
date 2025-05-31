package com.product.service.impl;

import com.product.dto.ProductRequest;
import com.product.dto.ProductResponse;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setId(productRequest.id());
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setPrice(productRequest.price());
        Product saveProduct= productRepository.save(product);
        log.info("\u001B[32mProduct saved successfully with id {}\u001B[0m", saveProduct.getId());
        return convertProductToProductResponse(saveProduct);
    }

    @Override
    public List<ProductResponse> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertProductToProductResponse).toList();
    }

    private ProductResponse convertProductToProductResponse(Product product) {
        return new ProductResponse(product.getId(),product.getName(),
                product.getDescription(),product.getPrice());
    }

}
