package application.controller.api;

import application.controller.BaseController;
import application.data.model.product.Product;
import application.data.service.ProductService;
import application.model.ResponseModel;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class ProductApiController extends BaseController<ProductService, Product> {

    private ProductService productService;

    public ProductApiController(ProductService productService) {
        setService(productService);
        this.productService = productService;
    }

    @PostMapping("/search")
    public ResponseModel search(@RequestBody Product product){
        return getService().search(product.getProductId());
    }
}
