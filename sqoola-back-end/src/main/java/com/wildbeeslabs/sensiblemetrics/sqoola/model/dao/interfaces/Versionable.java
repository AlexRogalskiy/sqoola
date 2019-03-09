package com.wildbeeslabs.sensiblemetrics.sqoola.model.dao.interfaces;

import java.io.Serializable;

public interface Versionable<T extends Serializable> extends Serializable {

    T getVersion();

    void setVersion(final T version);
}
