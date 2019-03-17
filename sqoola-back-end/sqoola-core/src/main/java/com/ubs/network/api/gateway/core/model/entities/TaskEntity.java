package com.ubs.network.api.gateway.core.model.entities;

import com.ubs.network.api.rest.common.model.entities.BaseUnitEntity;
import com.ubs.network.api.rest.common.model.interfaces.IManageableUnit;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class TaskEntity extends BaseUnitEntity implements IManageableUnit {
    @Id
    @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;

    @Column(name="OPTION_VALUE")
    private String value;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getId() + "," + getValue();
    }
}
