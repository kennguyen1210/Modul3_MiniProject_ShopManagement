package ra.business.model;

import ra.business.util.FormatTime;

import java.io.Serializable;
import java.time.LocalDate;

public class Catalog implements Serializable {
    private Long catalogId;
    private String catalogName;
    private String description;
    private LocalDate create_at;
    private LocalDate update_at;
    private boolean status = true;

    public Catalog() {
    }

    public Catalog(Long catalogId, String catalogName, String description, LocalDate create_at, LocalDate update_at) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.create_at = create_at;
        this.update_at = update_at;
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

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus() {
        this.status = !this.status;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", description='" + description + '\'' +
                ", create_at=" + FormatTime.formatTime(create_at) +
                ", update_at=" + FormatTime.formatTime(update_at) +
                ", status=" + status +
                '}';
    }
}
