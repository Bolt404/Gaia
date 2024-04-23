package org.apollo.template.Domain;

public class CamperType {

    private String type;
    private String typeDescription;

    public CamperType(String type, String typeDescription) {
        this.type = type;
        this.typeDescription = typeDescription;
    }

    public String getType() {
        return type;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    @Override
    public String toString() {
        return getType();
    }
}
