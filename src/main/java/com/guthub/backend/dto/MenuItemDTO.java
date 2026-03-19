package com.guthub.backend.dto;

public class MenuItemDTO {
    private Long id;
    private String itemName;
    private String description;
    private Boolean celiacCertified;

    public MenuItemDTO() {}

    public MenuItemDTO(Long id, String itemName, String description, Boolean celiacCertified) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.celiacCertified = celiacCertified;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getCeliacCertified() { return celiacCertified; }
    public void setCeliacCertified(Boolean celiacCertified) { this.celiacCertified = celiacCertified; }
}