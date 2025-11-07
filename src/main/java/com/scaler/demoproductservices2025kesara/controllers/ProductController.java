package com.scaler.demoproductservices2025kesara.controllers;

import com.scaler.demoproductservices2025kesara.dtos.Userdto;
import com.scaler.demoproductservices2025kesara.exception.ProductNotFoundException;
import com.scaler.demoproductservices2025kesara.exception.UnAuthorizedException;
import com.scaler.demoproductservices2025kesara.models.Product;
import com.scaler.demoproductservices2025kesara.service.ProductService;
import com.scaler.demoproductservices2025kesara.util.AuthUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(@Qualifier("SelfProductService")ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}/{token}")
    public Product getproductbyid(@PathVariable("id") Long id,@PathVariable ("token") String token) throws ProductNotFoundException, UnAuthorizedException {

//        ResponseEntity<Product> responseEntity = null;
//        try{
//            Product product= productService.getProductbyid(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        }catch (ProductNotFoundException e){
//            responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        }

// make a call to user service to validate token
        Userdto userdto = AuthUtil.validateToken(token);
        if(userdto == null) {
            throw new UnAuthorizedException("you cant access this recourse,Please login first");
        }

        return productService.getProductbyid(id);
        //throw new RuntimeException("Something went wrong");
        // instead of controller throw exception at service
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> producterror(ProductNotFoundException e) {
        return new ResponseEntity<>(
                    e.getMessage(),
                HttpStatus.BAD_REQUEST
                );
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getproductbyid(@PathVariable("id") Long id){
//
//        ResponseEntity<Product> responseEntity = null;
//        try{
//            Product product= productService.getProductbyid(id);
//            responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
//        }catch (ProductNotFoundException e){
//            responseEntity = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
//        }
//
//
//
//        return responseEntity;
//        //throw new RuntimeException("Something went wrong");
//        // instead of controller throw exception at service
//    }

//    3rd class
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getproductbyid(@PathVariable("id") Long id){
//        Product product= productService.getProductbyid(id);
//      return new ResponseEntity<>(product,
//                                   HttpStatus.OK);
//        //throw new RuntimeException("Something went wrong");
//        // instead of controller throw exception at service
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getproductbyid(@PathVariable("id") Long id){
//        Product product= productService.getProductbyid(id);
//        return new ResponseEntity<>(product,
//                                    HttpStatus.OK);
//    }

//    2nd class
//    @GetMapping("/{id}")
//    public Product getproductbyid(@PathVariable("id") Long id){
//        return productService.getProductbyid(id);
//    }
//    @GetMapping()
//    public List<Product> getallproduct(){
//        return productService.getallproduct();
//    }
    //pagination
    @GetMapping()
    public Page<Product> getallproduct(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getallproduct(pageNumber,pageSize);
    }



/* 1st class
    @GetMapping("/{id}")
    public Product getproductbyid(@PathVariable("id") Long id){
        return new Product();
    }

    @GetMapping()
    public List<Product> getallproduct(){
        return new ArrayList<Product>();
    }

    @PostMapping()
    public Product createproduct(@RequestBody Product product){
        return null;
    }
    @PatchMapping("/{id}")
    public Product updateproduct(@PathVariable("id") Long productid     ,@RequestBody Product product){
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable("id") Long productid){

    }

     */

    //last class
    @PostMapping()
    public Product createproduct(@RequestBody Product product){

        return productService.createproduct(product);
    }

    @PutMapping("/{id}")
    public Product replaceproduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        //for this id replace the existing product with updated product
        return productService.replaceproduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteproduct(@PathVariable("id") Long id){
        productService.deleteproduct(id);
    }
}
