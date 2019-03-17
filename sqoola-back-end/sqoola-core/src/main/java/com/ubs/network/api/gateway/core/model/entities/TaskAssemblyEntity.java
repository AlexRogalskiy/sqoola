package com.ubs.network.api.gateway.core.model.entities;

import com.ubs.network.api.rest.common.model.entities.BaseDataEntity;
import com.ubs.network.api.rest.common.model.interfaces.IManageableUnit;

import java.util.List;

public class TaskAssemblyEntity extends BaseDataEntity {
    public List<IManageableUnit> getTaskUnits() {
        return results;
    }

    public List<TaskScheduleEntity> getSchedules();

    /**
     * Returns whether the Order has the given id.
     *
     * @param id
     * @return
     */
    public boolean hasId(final Long id) {
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
