package application.data.service;

import application.data.model.product.Product;
import application.data.repository.product.ProductRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<ProductRepository, Product> {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        setRepository(productRepository);
        this.productRepository = productRepository;
    }



}
