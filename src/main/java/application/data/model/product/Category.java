package application.data.model.product;

import application.data.model.Details;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
@Component
@Entity(name = "tbl_category")
public class Category extends Details {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer categoryId;
    private String name;
    private String shortDesc;
    private Integer parentId;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
