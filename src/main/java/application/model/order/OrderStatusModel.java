package application.model.order;

import java.util.Date;

public class OrderStatusModel {
    private int id;
    private String name;
    private String desc;
    private Date createdDate;
    private Date updatedDate;

    public OrderStatusModel(int id, String name, String desc, Date createdDate, Date updatedDate) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public OrderStatusModel() {
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
