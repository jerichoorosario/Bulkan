package com.team.bulkan.model;

public class ItemKit {

    private String stateTitle;
    private Boolean checked = false;

    public ItemKit(String stateTitle, boolean checked){
        this.stateTitle = stateTitle;
        this.checked = checked;
    }

    public String getItemKitTitle() {
        return stateTitle;
    }

    public void setItemKitTitle(String stateTitle) {
        this.stateTitle = stateTitle;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
