package com.scaler.demoproductservices2025kesara.repository;

//import com.scaler.demoproductservices2025kesara.Projection.ProductwithTitleAndPrice;
import com.scaler.demoproductservices2025kesara.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Optional<Product> findById(Long productid);
    //select * from prouct where id = productid

//    @Override
//    List<Product> findAll();
//    //select * from products

    @Override
    Page<Product> findAll(Pageable pageable);
    //select * from products

    @Override
    Product save(Product product);

    //custom queries
    //select title and price from produts table
    //done by HQL OR SQL
//    @Query()
//    List<ProductwithTitleAndPrice> getproductTitleAndprice(String title, double price);
//    //we need to make changes in the test cases which will be done afterwards
}
