package application.data.repository.product;


import application.data.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "select p.* from tbl_product p where (p.productid = :productId or :productId is null)", nativeQuery = true)
    ArrayList<Product> search(@Param("productId") Integer productId);
}
