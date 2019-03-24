//package application.controller.web;
//
//
//
//import application.data.model.*;
//import application.data.model.order.Order;
//import application.data.model.product.Category;
//import application.data.model.product.product;
//import application.data.service.CategoryService;
//import application.data.service.OrderService;
//import application.data.service.ProductService;
//import application.model.product.CategoryDetailModel;
//import application.model.product.CategoryInfor;
//import application.service.UserService;
//import application.viewmodel.common.ProductVM;
//import application.viewmodel.homelanding.BannerVM;
//import application.viewmodel.homelanding.HomeLandingVM;
//import application.viewmodel.homelanding.MenuItemVM;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.security.Principal;
//import java.util.*;
//
//import static application.constant.Constant.DEFAULT_PARENT_ID;
//import static application.constant.StatusOrderConstant.unpaid;
//
//@Controller
//@RequestMapping(path="/")
//@CrossOrigin
//public class HomeController{
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
//    @GetMapping(path="/list-products")
//    public String index(Model model,
//                        @RequestParam(value="pageSize", required=false)
//                        String ps,
//                        @RequestParam(value="pageNumber", required=false)
//                        String pn){
//
//        try {
//            int pageSize = Integer.parseInt(ps);
//            int pageNumber = Integer.parseInt(pn);
//            if(pageSize > 0 && pageNumber >= 0) {
//                model.addAttribute("paginableItem",
//                    productService.getListProducts(pageSize, pageNumber));
//            } else {
//                model.addAttribute("paginableItem",
//                    productService.getListProducts(10, 0));
//            }
//        } catch (Exception ex) {
//            model.addAttribute("paginableItem",
//                productService.getListProducts(10, 0));
//        }
//
//        return "list_product";
//    }
//
//    @GetMapping(path="/")
//    public String landing(Model model,
//                          HttpServletResponse response,
//                          @RequestHeader("User-Agent") String userAgent,
//                          HttpServletRequest request,
//                          final Principal principal) {
//
//
//        System.out.println("====================");
//        System.out.println(userAgent);
//        System.out.println("IP: " + request.getRemoteAddr());
//
//        Cookie cookies[] = request.getCookies();
//        UUID uuid = UUID.randomUUID();
//        String guid = uuid.toString();
//        boolean flag_guild = true;
//        String user_guild = "";
//
//        if (cookies != null) {
//            Arrays.stream(cookies)
//                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
//
//            for(Cookie c : cookies){
//                if(c.getName().equals("User_Guild")){
//                    if(c.getValue() != null){
//                        flag_guild = false;
//                        user_guild = c.getValue();
//                    }
//                }
//            }
//        }
//
//
//        if(principal == null && flag_guild ){
//            response.addCookie(new Cookie("User_Guild",guid));
//            System.out.print("User Unknown : ");
//            System.out.println(guid);
//            orderService.createOrderByUserguild(guid);
//            Order order = orderService.findOrderByUserguild(guid);
//            response.addCookie(new Cookie("OrderId",Integer.toString(order.getOrderId())));
//            response.addCookie(new Cookie("User_Id", null));
//        }else if(principal == null && !flag_guild){
//            System.out.print("User Unknown is checked : ");
//            System.out.println(user_guild);
//            response.addCookie(new Cookie("User_Id", null));
//        }else if(principal != null && !flag_guild) {
//            String userId = userService.findUserByUsername(principal.getName()).getId();
//            String updateUserId = orderService.setUserGuild(user_guild,userId);
//            System.out.println("Update UserId into Order :"+updateUserId);
//            response.addCookie(new Cookie("User_Id", userId));
//        }
//        else if(principal != null && flag_guild){
//            String userid = userService.findIdByUsername(principal.getName());
//            response.addCookie(new Cookie("User_Id",userid));
//            Order existOrder = orderService.findOrderByUserIdAndStatusid(userid,unpaid);
//            if(existOrder != null){
//                response.addCookie(new Cookie("OrderId",Integer.toString(existOrder.getOrderId())));
//                response.addCookie(new Cookie("User_Guild",existOrder.getUserGuild()));
//            }else {
//                if(orderService.createOrderByUserIdAndUser_guild(userid,guid)){
//                    existOrder = orderService.findOrderByUserIdAndStatusid(userid,unpaid);
//                    response.addCookie(new Cookie("OrderId",Integer.toString(existOrder.getOrderId())));
//                    response.addCookie(new Cookie("User_Guild",guid));
//                }
//            }
//        }
//
//
//
//
//        HomeLandingVM vm = new HomeLandingVM();
//
////        this.setLayoutHeaderVM(vm);
//
//        ModelMapper modelMapper = new ModelMapper();
//
//
//        ArrayList<BannerVM> listBanners = new ArrayList<>();
//        listBanners.add(new BannerVM("https://media.static-adayroi.com/sys_master/h75/hac/15516679143454.jpg", "Tươi"));
//        listBanners.add(new BannerVM("http://www.creavini.it/wp-content/uploads/2017/05/uva.png", "Nho Mỹ"));
//        listBanners.add(new BannerVM("https://edeka-tank.de/wp-content/uploads/2017/01/Fotolia_43618946_Tomaten_mood.jpg", "Cà Chua"));
//        listBanners.add(new BannerVM("https://dalat.net.vn/images/uploads/gia-dau-tay-da-lat-2.jpg", "Dâu Tây"));
//
//        ArrayList<MenuItemVM> listVtMenuItems = new ArrayList<>();
//        for(CategoryInfor cat : getData()){
//            ArrayList<CategoryDetailModel> categoryDetailModels = new ArrayList<>();
//            listVtMenuItems.add(new MenuItemVM(cat.getParentid(),cat.getParentname(),"/"));
//            if(cat.getData() != null){
//                for(CategoryDetailModel categoryDetailModel : cat.getData()){
//                    categoryDetailModels.add(modelMapper.map(categoryDetailModel,CategoryDetailModel.class));
//                }
//                for(CategoryDetailModel categoryDetailModel : categoryDetailModels){
//                    listVtMenuItems.get(cat.getParentid() - DEFAULT_PARENT_ID).getChildren().add(new MenuItemVM(categoryDetailModel.getId(),categoryDetailModel.getName(),"/category/detail/"+categoryDetailModel.getId()));
//                }
//            }
//        }
//
//
//        PaginableItemList<product> paginableItemListHot = productService.getListProducts(8, 0);
//        ArrayList<ProductVM> listHotProductVMs = new ArrayList<>();
//        for(product product : paginableItemListHot.getListData()) {
//            ProductVM productVM = modelMapper.map(product, ProductVM.class);
//            listHotProductVMs.add(productVM);
//        }
//
//        PaginableItemList<product> paginableItemListTrend = productService.getListProducts(8, 1);
//        ArrayList<ProductVM> listTrendProductVMs = new ArrayList<>();
//        for(product product : paginableItemListTrend.getListData()) {
//            ProductVM productVM = modelMapper.map(product, ProductVM.class);
//            listTrendProductVMs.add(productVM);
//        }
//
//        vm.setListBanners(listBanners);
//        vm.setListVtMenuItemsAside(listVtMenuItems);
//        vm.setListHotProducts(listHotProductVMs);
//        vm.setListTrendProducts(listTrendProductVMs);
//
//        model.addAttribute("vm", vm);
//        return "index";
//    }
//
//    @GetMapping("/contact")
//    public String contact(){
//        return "/contact";
//    }
//
//    @GetMapping("/about")
//    public String about() {
//        return "/about";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "/login";
//    }
//
//    @GetMapping("/403")
//    public String error403() {
//        return "/error/403";
//    }
//
//    @GetMapping("/rules")
//    public String rules(){
//        return "/rules";
//    }
//
//    @GetMapping("/#_=_")
//    public String signup(){
//        return "index";
//    }
//
//    @GetMapping("/remote")
//    public String remote(){
//        return "garden/remote";
//    }
//
//    @GetMapping("/remote/temp")
//    public String temp(){
//        return "garden/temp";
//    }
//
//    @GetMapping("/remote/hum")
//    public String hum(){
//        return "garden/hum";
//    }
//
//    @GetMapping("/remote/soil")
//    public String soil(){
//        return "garden/soil";
//    }
//
//    @GetMapping("/test")
//    public String test(){
//        return "/admin/socket/user-chat";
//    }
//
//
//    public ArrayList<CategoryInfor> getData(){
//        ModelMapper modelMapper = new ModelMapper();
//        ArrayList<CategoryInfor> categoryInforArrayList = new ArrayList<>();
//        try {
//            List<Category> categoryList = categoryService.fillAll();
//            ArrayList<CategoryDetailModel> categoryDetailModelArrayList = new ArrayList<>();
//            for(Category cat : categoryList){
//                categoryDetailModelArrayList.add(modelMapper.map(cat,CategoryDetailModel.class));
//            }
//            for (CategoryDetailModel cat : categoryDetailModelArrayList){
//                CategoryInfor categoryInfor = new CategoryInfor();
//                if(getChil(cat.getId()).size() > 0){
//                    categoryInfor.setData(getChil(cat.getId()));
//                    categoryInfor.setParentname(cat.getName());
//                    categoryInfor.setParentid(cat.getId());
//                    categoryInforArrayList.add(categoryInfor);
//                }else if(getChil(cat.getId()).size() == 0 && cat.getParentid() == 0) {
//                    categoryInfor.setData(null);
//                    categoryInfor.setParentname(cat.getName());
//                    categoryInfor.setParentid(cat.getId());
//                    categoryInforArrayList.add(categoryInfor);
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return categoryInforArrayList;
//    }
//
//
//    public ArrayList<CategoryDetailModel> getChil(int id){
//        List<Object[]> list = categoryService.findByParentId(id);
//        ArrayList<CategoryDetailModel> categoryDetailModels = new ArrayList<>();
//        ModelMapper modelMapper = new ModelMapper();
//        for(Object cat : list){
//            categoryDetailModels.add(modelMapper.map(cat,CategoryDetailModel.class));
//        }
//        return categoryDetailModels;
//    }
//
//
//
//
//}
