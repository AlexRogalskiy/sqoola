package com.ubs.network.api.gateway.core.model.entities;

import com.ubs.network.api.rest.common.model.entities.ABaseEntity;

public class TaskSubscriptionEntity extends ABaseEntity {
    @Id
    @GeneratedValue
    @Column(name="VOTE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="OPTION_ID")
    private Option option;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Option getOption() {
        return option;
    }
    public void setOption(Option option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return getId() + ", " + getOption();
    }
}
