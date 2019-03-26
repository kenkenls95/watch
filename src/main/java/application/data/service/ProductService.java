package application.data.service;

import application.data.model.product.Product;
import application.data.repository.product.ProductRepository;
import application.model.Meta;
import application.model.MetaDataStatus;
import application.model.ResponseModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import static application.common.Common.setMetaData;

@Service
public class ProductService extends BaseService<ProductRepository, Product> {

    private static final Logger logger = LogManager.getLogger(ProductService.class);

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        setRepository(productRepository);
        this.productRepository = productRepository;
    }

    public ResponseModel search(Integer id){
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            return setMetaData(responseModel, getRepository().search(id));
        } catch (Exception e) {
            e.printStackTrace();
            return setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }


}
