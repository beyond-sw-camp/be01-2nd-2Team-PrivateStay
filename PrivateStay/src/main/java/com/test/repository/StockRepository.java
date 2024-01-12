package com.test.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.entity.Product;

public interface StockRepository extends JpaRepository<Product, Integer>{ //Type, Key

}
 