package com.scaler.demoproductservices2025kesara.service;

import com.scaler.demoproductservices2025kesara.dtos.FakeStoreProductdto;
import com.scaler.demoproductservices2025kesara.exception.ProductNotFoundException;
import com.scaler.demoproductservices2025kesara.models.Category;
import com.scaler.demoproductservices2025kesara.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService {
    private RestTemplate restTemplate;
    //this service implementatiom uses fakestore to fetch product
    private RedisTemplate<String,Object> redisTemplate;


    public FakeStoreProductService(RestTemplate restTemplate,
                                   RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;

    }
    /*we ceate an obj here but spring will find the value from original bean
    ie we have resttemplate in the memory and we have use that in obj reference
     */

    private Product convertFakeProductDtoToProduct(FakeStoreProductdto fakeStoreProductdto) {
        Product product = new Product();
        product.setId(fakeStoreProductdto.getId());
        product.setTitle(fakeStoreProductdto.getTitle());
        product.setDescription(fakeStoreProductdto.getDescription());
        product.setImageurl(fakeStoreProductdto.getImage());
        product.setPrice(fakeStoreProductdto.getPrice());
        Category category = new Category();
        category.setName(fakeStoreProductdto.getCategory());
        product.setCategory(category);
//        product.setCategory(new Category());
//        product.getCategory().setName(fakeStoreProductdto.getCategory());

        return product;
    }


    @Override
    public Product getProductbyid(Long productid) throws ProductNotFoundException {
        //make a API call to fakestore and get the given id
        //'https://fakestoreapi.com/products/1'
//        throw new RuntimeException("Something went wrong");
        //calling to fakestore 3rd party system
        //1st check in the redis whether the product is present or not
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT" + productid) ;;

       //CACHE HIT
        if(product != null){//1st check if product is present there then return it
           return product;
        }

        //cache miss
        FakeStoreProductdto fakeStoreProductdto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productid,
                FakeStoreProductdto.class);
        if (fakeStoreProductdto == null) {
            throw new ProductNotFoundException("product with id "+ productid +" doesnot exist" );
        }
        //convert fakestore dto into product obj
        product = convertFakeProductDtoToProduct(fakeStoreProductdto);
        redisTemplate.opsForHash().put("PRODUCTS","PRODUCT"+productid,product);
        return product;


    }
//    @Override
//    public Product getProductbyid(Long productid) throws ProductNotFoundException {
//        //make a API call to fakestore and get the given id
//        //'https://fakestoreapi.com/products/1'
/// /        throw new RuntimeException("Something went wrong");
///
/// @return
//        FakeStoreProductdto fakeStoreProductdto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + productid,
//                FakeStoreProductdto.class);
//        if (fakeStoreProductdto == null) {
//            throw new ProductNotFoundException("product with id "+ productid +"doesnot exist" );
//        }
//        //convert fakestore dto into product obj
//        return convertFakeProductDtoToProduct(fakeStoreProductdto);
//
//
//    }

//    @Override
//    public Product getProductbyid(Long productid) {
//        //make a API call to fakestore and get the given id
//        //'https://fakestoreapi.com/products/1'
//        FakeStoreProductdto fakeStoreProductdto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/" + productid,
//                FakeStoreProductdto.class);
//        //convert fakestore dto into product obj
//        return convertFakeProductDtoToProduct(fakeStoreProductdto);
//
//    }
    @Override
    public List<Product> getallproduct() {
        FakeStoreProductdto[] fakeStoreProductdtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductdto[].class
        );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductdto fakeStoreProductdto : fakeStoreProductdtos) {
            products.add(convertFakeProductDtoToProduct(fakeStoreProductdto));
        }
        return products;
    }
//pagination
    @Override
    public Page<Product> getallproduct(int pageNumber, int pageSize) {
        FakeStoreProductdto[] fakeStoreProductdtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductdto[].class
        );

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductdto fakeStoreProductdto : fakeStoreProductdtos) {
            products.add(convertFakeProductDtoToProduct(fakeStoreProductdto));
        }
        return new PageImpl<>(products);
    }

    //last class
    @Override
    public Product createproduct(Product product) {
        return null;
    }

    @Override
    public Product replaceproduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public void deleteproduct(Long id) {

    }


}
