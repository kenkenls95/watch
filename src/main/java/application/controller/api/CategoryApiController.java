package application.controller.api;

import application.common.Common;
import application.controller.BaseController;
import application.data.model.product.Category;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.*;
import application.model.product.CategoryDetailModel;
import application.model.product.CategoryInfor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController extends BaseController<CategoryService, Category> {

    private CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        setService(categoryService);
        this.categoryService = categoryService;
    }

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/child")
    public ResponseModel getChild() {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            List<Category> categoryList1 = categoryService.search(null);
            List<Category> categoryList2 = categoryService.search(null);
            ArrayList<CategoryInfor> categoryInfors = new ArrayList<>();
            for (Category category1 : categoryList1) {
                CategoryInfor child = modelMapper.map(category1, CategoryInfor.class);
                ArrayList<Category> data = new ArrayList<>();
                for (Category category2 : categoryList2) {
                    if (category1.getCategoryId() == category2.getParentId()) {
                        data.add(category2);
                    }
                }
                child.setData(data);
                categoryInfors.add(child);
            }
            Common.setMetaData(responseModel, categoryInfors);
        } catch (Exception e) {
            e.printStackTrace();
            Common.setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
        return responseModel;

    }

    @PostMapping("/search")
    public ResponseModel search(@RequestBody Category category) {
        ResponseModel responseModel = new ResponseModel();
        Meta meta = new Meta();
        try {
            ArrayList<Category> list = categoryService.search(category.getCategoryId());
            return Common.setMetaData(responseModel, list);
        } catch (Exception e) {
            return Common.setMetaData(responseModel, meta, MetaDataStatus.FAILED);
        }
    }

}
