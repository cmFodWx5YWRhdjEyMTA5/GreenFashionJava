package com.zeowls.data.mapper;

import com.zeowls.data.entity.CurrentState_data;
import com.zeowls.domain.entity.CurrentState;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class StateMapper extends Mapper<CurrentState, CurrentState_data> {
    @Inject
    public StateMapper() {
    }


    @Override
    public CurrentState map(CurrentState_data data) {
        if (data == null) return null;
        CurrentState entity = new CurrentState();
        entity.setActive(data.getActive());
        entity.setDateAdd(data.getDateAdd());
        entity.setDateUpd(data.getDateUpd());
        entity.setId(data.getId());
        entity.setDescription(data.getDescription());
        entity.setName(data.getName());
        return entity;
    }

    @Override
    public CurrentState_data reverse(CurrentState currentState) {
        return null;
    }
}
