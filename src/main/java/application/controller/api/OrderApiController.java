package application.controller.api;

import application.controller.BaseController;
import application.data.model.order.Order;
import application.data.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderApiController extends BaseController<OrderService, Order> {

    private OrderService orderService;

    public OrderApiController(OrderService orderService){
        setService(orderService);
        this.orderService = orderService;
    }

}

