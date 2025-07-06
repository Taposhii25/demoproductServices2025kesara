package com.scaler.demoproductservices2025kesara.service;

import com.scaler.demoproductservices2025kesara.exception.ProductNotFoundException;
import com.scaler.demoproductservices2025kesara.models.Category;
import com.scaler.demoproductservices2025kesara.models.Product;
import com.scaler.demoproductservices2025kesara.repository.CategoryRepository;
import com.scaler.demoproductservices2025kesara.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductService")

public class SelfproductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfproductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductbyid(Long productid) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(productid);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getallproduct() {
        return productRepository.findAll();
    }

    //    @Override
//    public List<Product> getallproduct() {
//        return productRepository.findAll();
//    }
    //pagination
    @Override
    public Page<Product> getallproduct(int pageNumber, int pageSize) {
        //pageable is an interface we cant create obj from it
        //pagenum ~ offset, page size ~ limit
        return productRepository.findAll(
                PageRequest.of(pageNumber, pageSize)
        );
    }

    //last class
    @Override
    public Product createproduct(Product product) {
        //before saving the product we should see whether the category
        //is present in db or not if not save category in db
        //provide a category repository
        Category category = product.getCategory();
        Optional<Category> optionalCategory = categoryRepository.findByName(category.getName());
        if(optionalCategory.isEmpty()){
            //save the category
            category =categoryRepository.save(category);
        }else {
            // Use the existing one
            category = optionalCategory.get();
        }
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Product replaceproduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id " +id+ " not found");
        }
        Product productfromDB = optionalProduct.get();

        productfromDB.setTitle(product.getTitle());
        productfromDB.setPrice(product.getPrice());
        productfromDB.setDescription(product.getDescription());
        productfromDB.setImageurl(product.getImageurl());

        Category category = product.getCategory();
        if(category.getId() == null){
            //save the category first in db
            category= categoryRepository.save(product.getCategory());
        }
        productfromDB.setCategory(category);
        return productRepository.save(productfromDB);


    }

    @Override
    public void deleteproduct(Long id) {
        productRepository.deleteById(id);
    }
}
