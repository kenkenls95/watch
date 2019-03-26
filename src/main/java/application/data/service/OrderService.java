package application.data.service;

import application.data.model.order.Order;
import application.data.repository.product.OrderRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseService<OrderRepository, Order> {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private OrderRepository orderRepository;

    public OrderService (OrderRepository orderRepository){
        setRepository(orderRepository);
        this.orderRepository = orderRepository;
    }

}
