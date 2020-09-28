package com.alex.java.repo;

import com.alex.java.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductInfoRepo extends JpaRepository<ProductInfo, String>{

    Optional<ProductInfo> findProductInfosByProductId(String id);

    List<ProductInfo> findByProductStatus(Integer status);

}
