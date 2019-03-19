package com.sensiblemetrics.api.sqoola.common.cache.entry;

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
    private static final long serialVersionUID = -1679248864531907771L;

    /**
     * Event service code
     */
    private String serviceCode;
    /**
     * Event error code
     */
    private String errorCode;
    /**
     * Event max count
     */
    private int maxCount;
    /**
     * Collection {@link List} of cache subscribers
     */
    private final List<String> subscribers = new ArrayList<>();

    /**
     * Sets collection of cache subscribers {@link Collection}
     *
     * @param subscribers - initial input collection of subscribers {@link Collection}
     */
    public void setSubscribers(final Iterable<? extends String> subscribers) {
        this.getSubscribers().clear();
        Optional.ofNullable(subscribers)
            .orElseGet(Collections::emptyList)
            .forEach(this::addSubscriber);
    }

    /**
     * Adds subscriber {@link String} to cache collection {@link Collection}
     *
     * @param subscriber - initial input subscriber {@link String}
     */
    public void addSubscriber(final String subscriber) {
        if (Objects.nonNull(subscriber)) {
            this.getSubscribers().add(subscriber);
        }
    }
}

