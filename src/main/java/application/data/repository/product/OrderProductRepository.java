package application.data.repository.product;

import application.data.model.order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {



}
