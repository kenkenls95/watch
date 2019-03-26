package application.data.model.order;

import application.data.model.Details;

import javax.persistence.*;

@Entity(name = "tbl_orderproduct")
public class OrderDetails extends Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderDetailsId;
    private Integer product;
    private Integer orderId;
    private Double orderPrice;
    private Integer orderQuantity;


    public Integer getOrderDetailsId() {
        return orderDetailsId;
    }

    public void setOrderDetailsId(Integer orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
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
