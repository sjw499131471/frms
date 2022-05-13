package com.sjw.frms.model;

public class ResourceType {
    private String typeId;

    private String typeName;

    private String parentType;

    private String typeState;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType == null ? null : parentType.trim();
    }

    public String getTypeState() {
        return typeState;
    }

    public void setTypeState(String typeState) {
        this.typeState = typeState == null ? null : typeState.trim();
    }
}