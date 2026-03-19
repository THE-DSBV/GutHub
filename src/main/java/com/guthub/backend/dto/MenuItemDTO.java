package com.guthub.backend.dto;

public class MenuItemDTO {
    private String itemName;
    private String description;
    private Boolean celiacCertified;

    public MenuItemDTO(String itemName, String description, Boolean celiacCertified) {
        this.itemName = itemName;
        this.description = description;
        this.celiacCertified = celiacCertified;
    }

    // Getters
    public String getItemName() { return itemName; }
    public String getDescription() { return description; }
    public Boolean getCeliacCertified() { return celiacCertified; }
}