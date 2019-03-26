package application.data.model.product;

import application.data.model.Details;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity(name = "tbl_product")
public class Product extends Details{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productid")
    @Id
    private Integer productId;
    private String name;
    private String image;
    private Integer amount;
    private String shortDesc;
    private Integer categoryId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
