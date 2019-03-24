package application.data.service;

import application.data.model.product.Category;
import application.data.repository.product.CategoryRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService extends BaseService<CategoryRepository,Category> {

    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        setRepository(categoryRepository);
        this.categoryRepository = categoryRepository;
    }


    public ArrayList<Category> search(Integer categoryId) {
        try {
            return categoryRepository.search(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
