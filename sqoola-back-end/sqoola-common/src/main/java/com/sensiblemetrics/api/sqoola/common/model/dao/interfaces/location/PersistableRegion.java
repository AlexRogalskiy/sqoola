package com.sensiblemetrics.api.sqoola.common.model.dao.interfaces.location;

/**
 * Persistable region model definition
 */
public interface PersistableRegion {

    /**
     * Default document ID
     */
    String MODEL_ID = "Region";
    /**
     * Default table name
     */
    String TABlE_NAME = "regions";

    /**
     * Default field names
     */
    String ID_FIELD_NAME = "id";
    String ATTRIBUTE_ID_FIELD_NAME = "attributeId";
    String NAME_FIELD_NAME = "name";
    String SYNONYM_FIELD_NAME = "synonym";
    String DESCRIPTION_TEXT_FIELD_NAME = "descriptionText";
    String KEYWORDS_FIELD_NAME = "keywords";
    String PRODUCTS_FIELD_NAME = "products";
    /**
     * Default reference field names
     */
    String ATTRIBUTES_REF_FIELD_NAME = "regions";
}
