package com.ubs.network.api.gateway.core.model.entities;

import com.ubs.network.api.rest.common.model.entities.BaseDataEntity;

public class TaskWorkFlowEntity extends BaseDataEntity {

    private String requestNumber;
    private UserAccount requestedBy ;
    private UserAccount assignedTo;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDate dob;
}
