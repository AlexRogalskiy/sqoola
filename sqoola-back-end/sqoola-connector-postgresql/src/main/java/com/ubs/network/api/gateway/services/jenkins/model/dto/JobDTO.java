package com.ubs.network.api.gateway.services.jenkins.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ubs.network.api.rest.common.model.dto.BaseResourceSupportDTO;
import com.ubs.network.api.rest.common.model.dto.BaseUnitDTO;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobDTO extends BaseUnitDTO {

    /**
     * DTO for {@link Order}s.
     *
     * @author Oliver Gierke
     */
    @RequiredArgsConstructor
    @Getter
    private static class JobResourceSupportDTO extends BaseResourceSupportDTO {

        private final List<LineItem> lineItems;
    }
}
