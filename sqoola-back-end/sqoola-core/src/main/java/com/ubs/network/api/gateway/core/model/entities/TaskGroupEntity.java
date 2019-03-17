package com.ubs.network.api.gateway.core.model.entities;

import com.ubs.network.api.rest.common.model.entities.BaseGroupUnitEntity;
import com.ubs.network.api.rest.common.model.interfaces.IManageableUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class TaskGroupEntity extends BaseGroupUnitEntity implements IManageableUnit {
    private Long id;
    private int totalCount;
    private String quote;
    private List<TaskEntity> tasks;

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalVotes(int totalCount) {
        this.totalCount = totalCount;
    }
    public List<TaskEntity> getResults() {
        return results;
    }
    public void setResults(final List<TaskEntity> results) {
        this.results = results;
    }

    public String getQuote() {
        return this.quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    /**
     * Returns whether the Order has the given id.
     *
     * @param id
     * @return
     */
    public boolean hasId(Long id) {
        return this.id.equals(id);
    }

    /**
     * Returns whether the {@link Order} belongs to the given {@link Customer}.
     *
     * @param customer
     * @return
     */
    public boolean belongsTo(final Customer customer) {
        return this.customer.equals(customer);
    }

    /**
     * Returns whether the {@link Order} belongs to the given {@link Customer}.
     *
     * @param customer
     * @return
     */
    public boolean contains(final TaskEntity task) {
        return this.results.contains(task);
    }
}
