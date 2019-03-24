package application.data.model.product;

import application.data.model.Details;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity(name = "tbl_pricing")
public class Pricing extends Details {
    @Id
    private Integer pricingId;
    private Date effectiveDate;
    private Double price;
    private Integer productId;
    private Double discount;

    public Integer getPricingId() {
        return pricingId;
    }

    public void setPricingId(Integer pricingId) {
        this.pricingId = pricingId;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
