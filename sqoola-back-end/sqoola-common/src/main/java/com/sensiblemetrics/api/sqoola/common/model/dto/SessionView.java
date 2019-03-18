package com.sensiblemetrics.api.sqoola.common.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableSessionView;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.ID_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableBaseModelView.SCORE_FIELD_NAME;
import static com.sensiblemetrics.api.sqoola.common.model.dto.interfaces.ExposableProductView.*;

/**
 * Session model view {@link BaseModelView}
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder(value = {
    ID_FIELD_NAME,
    SCORE_FIELD_NAME,
    NAME_FIELD_NAME,
    SHORT_DESCRIPTION_FIELD_NAME,
    LONG_DESCRIPTION_FIELD_NAME,
    PRICE_DESCRIPTION_FIELD_NAME,
    CATALOG_NUMBER_FIELD_NAME,
    PAGE_TITLE_FIELD_NAME,
    AVAILABLE_FIELD_NAME,
    PRICE_FIELD_NAME,
    RECOMMENDED_PRICE_FIELD_NAME,
    RATING_FIELD_NAME,
    AGE_RESTRICTION_FIELD_NAME,
    LOCK_TYPE_FIELD_NAME,
    GEO_LOCATION_FIELD_NAME,
    ATTRIBUTES_FIELD_NAME,
    CATEGORIES_FIELD_NAME,
    MAIN_CATEGORIES_FIELD_NAME
}, alphabetic = true)
@JacksonXmlRootElement(localName = ExposableSessionView.VIEW_ID, namespace = "io.sqoola")
@ApiModel(value = ExposableSessionView.VIEW_ID, description = "All details about product document")
public class SessionView extends BaseModelView<String> implements ExposableSessionView {

    private Long id;
    private LocalDateTime logtime;
    private String request;
    private String referer;
    private String qstring;
    private String ipAddress;
    private String agent;
    private String errorMessage;
    private String errorCode;
}
