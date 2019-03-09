package com.wildbeeslabs.sensiblemetrics.sqoola.common.cache;

import lombok.*;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CacheEventEntry implements Serializable {

    /**
     * Default explicit serialVersionUID for interoperability
     */
    private static final long serialVersionUID = 461158514433131491L;

    private String serviceCode;
    private String errorCode;
    private int maxCount;
    private final List<String> subscribers = new ArrayList<>();

    public void setSubscribers(final Collection<? extends String> subscribers) {
        this.getSubscribers().clear();
        Optional.ofNullable(subscribers)
            .orElseGet(Collections::emptyList)
            .forEach(this::addSubscriber);
    }

    public void addSubscriber(final String subscriber) {
        if (Objects.nonNull(subscriber)) {
            this.getSubscribers().add(subscriber);
        }
    }
}

