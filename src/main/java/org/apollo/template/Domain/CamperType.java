package org.apollo.template.Domain;

/**
 * Represents a type of camper.
 * This class encapsulates information about a specific type of camper,
 * including its type and description.
 */
public class CamperType {

    private String type;
    private String typeDescription;

    /**
     * Constructs a CamperType object with the specified type and description.
     * @param type The type of the camper.
     * @param typeDescription The description of the camper type.
     */
    public CamperType(String type, String typeDescription) {
        this.type = type;
        this.typeDescription = typeDescription;
    }
    public CamperType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the type of the camper.
     * @return The type of the camper.
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the description of the camper type.
     * @return The description of the camper type.
     */
    public String getTypeDescription() {
        return typeDescription;
    }

    /**
     * Returns a string representation of the camper type.
     * This method returns the type of the camper.
     * @return The type of the camper.
     */
    @Override
    public String toString() {
        return getType();
    }
}
