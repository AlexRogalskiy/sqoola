package com.sensiblemetrics.api.sqoola.common.controller.wrapper;

import lombok.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Offset page request entity {@link Pageable}
 */
@Builder
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class OffsetPageRequest implements Pageable {

    /**
     * Default page offset
     */
    private long offset;
    /**
     * Default page limit
     */
    private int limit;
    /**
     * Default page sort
     */
    private Sort sort;

    /**
     * Default offset page request constructor with initial offset / limit input arguments
     *
     * @param offset - initial offset input argument
     * @param limit  - initial limit input argument
     */
    public OffsetPageRequest(long offset, int limit) {
        this(offset, limit, null);
    }

    public OffsetPageRequest(long offset, int limit, final Sort sort) {
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return this.limit;
    }

    @Override
    public long getOffset() {
        return this.offset;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return OffsetPageRequest
            .builder()
            .offset(getOffset() + getPageSize())
            .limit(getPageSize())
            .sort(getSort())
            .build();
    }

    @Override
    public Pageable previousOrFirst() {
        return OffsetPageRequest
            .builder()
            .offset(Math.max(0, getOffset() - getPageSize()))
            .limit(getPageSize())
            .sort(getSort())
            .build();
    }

    @Override
    public Pageable first() {
        return OffsetPageRequest
            .builder()
            .offset(0)
            .limit(getPageSize())
            .sort(getSort())
            .build();
    }

    @Override
    public boolean hasPrevious() {
        return getOffset() > 0;
    }
}
