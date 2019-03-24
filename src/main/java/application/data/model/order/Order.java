package application.data.model.order;

import application.data.model.Details;

import javax.persistence.*;
import java.util.*;

@Entity(name = "tbl_order")
public class Order extends Details {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private Integer userId;
    private String userGuild;
    private String address;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserGuild() {
        return userGuild;
    }

    public void setUserGuild(String userGuild) {
        this.userGuild = userGuild;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
