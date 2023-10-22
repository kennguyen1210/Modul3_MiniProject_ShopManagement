package ra.business.model;

import ra.business.util.FormatTime;
import ra.presentation.ShopManagement;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private Long productId;
    private String productName;
    private Long categoryId;
    private String description;
    private double unitPrice;
    private int stock;
    private LocalDate create_at;
    private LocalDate update_at;
    private boolean status = true;

    public Product() {
    }
    public Product(Long productId, String productName, Long categoryId, String description, double unitPrice, int stock, LocalDate create_at, LocalDate update_at) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.create_at = create_at;
        this.update_at = update_at;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public LocalDate getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(LocalDate update_at) {
        this.update_at = update_at;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus() {
        this.status = !this.status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", category=" + ShopManagement.catalogService.findById(categoryId).getCatalogName() +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", stock=" + stock +
                ", create_at=" + FormatTime.formatTime(create_at) +
                ", update_at=" + FormatTime.formatTime(update_at) +
                ", status=" + status +
                '}';
    }
}
