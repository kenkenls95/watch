package application.data.model.order;

import application.data.model.Details;
import application.data.model.product.Product;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_orderproduct")
public class OrderProduct extends Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderProductId;
    private Integer product;
    private Integer orderId;
    private Double orderPrice;
    private Integer orderQuantity;


    public Integer getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
