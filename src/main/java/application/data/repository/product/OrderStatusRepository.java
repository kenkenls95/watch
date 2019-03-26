package application.data.repository.product;

import application.data.model.order.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderDetails,Integer> {

}
