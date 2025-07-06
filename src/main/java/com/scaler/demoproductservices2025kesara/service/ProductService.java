package com.scaler.demoproductservices2025kesara.service;

import com.scaler.demoproductservices2025kesara.exception.ProductNotFoundException;
import com.scaler.demoproductservices2025kesara.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getProductbyid(Long productid) throws ProductNotFoundException;


    List<Product> getallproduct();

    //    @Override
    //    public List<Product> getallproduct() {
    //        return productRepository.findAll();
    //    }
        //pagination
    Page<Product> getallproduct(int pageNumber, int pageSize);

    Product createproduct(Product product);

    Product replaceproduct(Long id,Product product) throws ProductNotFoundException;



    void deleteproduct(Long id);
}
