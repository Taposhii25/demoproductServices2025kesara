package com.scaler.demoproductservices2025kesara.repository;

import com.scaler.demoproductservices2025kesara.models.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.function.Function;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String categoryName);
    //select * from category where name ?
    @Override
    Category save(Category category);

    @Override
    void deleteById(Long aLong);
}
