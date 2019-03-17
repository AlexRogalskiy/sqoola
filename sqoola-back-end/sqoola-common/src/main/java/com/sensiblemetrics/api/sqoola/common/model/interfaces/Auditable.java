package com.sensiblemetrics.api.sqoola.common.model.interfaces;

import java.io.Serializable;
import java.util.Date;

public interface Auditable extends Serializable {

    Date getCreated();

    void setCreated(final Date created);

    String getCreatedBy();

    void setCreatedBy(final String createdBy);

    Date getChanged();

    void setChanged(final Date changed);

    String getChangedBy();

    void setChangedBy(final String changedBy);
}
