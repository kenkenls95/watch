//package application.controller.api;
//
//import application.controller.BaseController;
//import application.data.model.order.Order;
//import application.data.model.order.OrderProduct;
//import application.data.model.product.Category;
//import application.data.model.product.Product;
//import application.data.model.user.User;
//import application.data.service.CategoryService;
//import application.data.service.OrderService;
//import application.data.service.ProductService;
//import application.service.UserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//
//@RestController
//@RequestMapping("/api/product")
//public class ProductApiController extends BaseController<ProductService, Product> {
//
//    private ProductService productService;
//    private Product product;
//
//    public ProductApiController(ProductService productService, Product product) {
//        setService(productService);
//        setObj(product);
//        this.productService = productService;
//        this.product = product;
//    }
//}
