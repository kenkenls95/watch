package application.data.repository.product;

import application.data.model.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>  {

    @Query(value = "select c.* from tbl_category c where (c.categoryid = :categoryId or :categoryId is null)",nativeQuery = true)
    ArrayList<Category> search(@Param("categoryId") Integer categoryId);
}
