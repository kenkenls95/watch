//package application.controller.web;
//
//import application.data.model.order.Order;
//import application.data.model.order.OrderDetails;
//import application.data.model.product.product;
//import application.data.model.user.User;
//import application.data.service.CategoryService;
//import application.data.service.OrderService;
//import application.data.service.ProductService;
//import application.model.order.OrderProductCheckOutModel;
//import application.model.product.ProductDetailModel;
//import application.model.product.ProductName;
//import application.service.UserService;
//import application.viewmodel.productindex.CheckOutVM;
//import application.viewmodel.productindex.ProductSearchVM;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.HttpClientErrorException;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.security.Principal;
//import java.util.ArrayList;
//import java.util.UUID;
//
//@Controller
//@RequestMapping(path="/product")
//public class ProductController extends BaseController {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/detail/{productId}")
//    public String index(Model model, @PathVariable int productId) {
//        product existProduct = productService.search(productId);
//        if(existProduct != null) {
//            ModelMapper modelMapper = new ModelMapper();
//            ProductDetailModel productDetailModel = modelMapper.map(existProduct, ProductDetailModel.class);
//
//            model.addAttribute("vm", productDetailModel);
//            return "product/index";
//        } else {
//            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String searchProduct(@ModelAttribute ProductName productName, BindingResult errors, Model model) {
//        ModelMapper modelMapper = new ModelMapper();
//        ProductSearchVM productSearchVM = new ProductSearchVM();
//        try {
//            ArrayList<product> products = new ArrayList<>();
//            ArrayList<ProductDetailModel> productDetailModels = new ArrayList<>();
//            products =  productService.findByNameContaining(productName.getName());
//            for(product p : products){
//                productDetailModels.add(modelMapper.map(p,ProductDetailModel.class));
//            }
//            productSearchVM.setKeyWord(productName.getName());
//            productSearchVM.setProductDetailModels(productDetailModels);
//            model.addAttribute("vm",productSearchVM);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "product/search";
//    }
//
//    @GetMapping("/order/checkout")
//    public String checkout(Model model,
//                           HttpServletResponse response,
//                           HttpServletRequest request,
//                           final Principal principal){
//        CheckOutVM checkOutVM =  new CheckOutVM();
//        ModelMapper modelMapper = new ModelMapper();
//        Cookie cookies[] = request.getCookies();
//        UUID uuid = UUID.randomUUID();
//        String guid = uuid.toString();
//        String user_guild = "";
//        int cartId = 0;
//
//        if (cookies != null) {
//            for(Cookie c : cookies){
//                if(c.getName().equals("User_Guild")){
//                    if(c.getValue() != null){
//                        user_guild = c.getValue();
//                    }
//                }
//                if(c.getName().equals("OrderId")){
//                    if(c.getValue() != null){
//                        cartId = Integer.parseInt(c.getValue());
//                    }
//                }
//            }
//        }
//
//        try {
//            Order order = orderService.findOrderByUserguild(user_guild);
//            System.out.println("====================");
//            System.out.println("Order by User_Guild :"+user_guild);
//            ArrayList<OrderDetails> orderProducts = orderService.getListOrderProductByOrderId(order.getOrderId());
//            ArrayList<OrderProductCheckOutModel> orderProductCheckOutModels =  new ArrayList<>();
//            int total = 0;
//            for(OrderDetails o : orderProducts){
//                ProductDetailModel productDetailModel = modelMapper.map(productService.search(o.getProductid()),ProductDetailModel.class);
//                orderProductCheckOutModels.add(new OrderProductCheckOutModel(o.getId(),productDetailModel.getImage(),productDetailModel.getName(),o.getOrderprice(),o.getOrderquantity()));
//                total += o.getOrderquantity()*o.getOrderprice();
//            }
//            checkOutVM.setOrderProductApiModels(orderProductCheckOutModels);
//            checkOutVM.setTotal(total);
//            if(check(order.getUserId())){
//                model.addAttribute("user" , new User());
//            }else {
//                User existUser = userService.findUserById(order.getUserId());
//                model.addAttribute("user" , existUser);
//            }
//            model.addAttribute("vm",checkOutVM);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        return "checkout";
//    }
//
//    public boolean check(String id){
//        if (id == null)
//            return true;
//        else if (id.trim().equals(""))
//            return true;
//        else
//            return false;
//    }
//
//}
