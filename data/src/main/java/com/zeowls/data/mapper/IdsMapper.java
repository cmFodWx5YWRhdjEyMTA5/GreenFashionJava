package com.zeowls.data.mapper;

import com.zeowls.data.entity.Ids_data;
import com.zeowls.domain.entity.Ids;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class IdsMapper extends Mapper<Ids, Ids_data> {


    @Inject
    public IdsMapper() {
    }


    @Override
    public Ids map(Ids_data data) {
        if (data == null) return null;
        Ids entity = new Ids();
        entity.setIds(data.getIds());
        return entity;
    }

    @Override
    public Ids_data reverse(Ids data) {
        if (data == null) return null;
        Ids_data entity = new Ids_data();
        entity.setIds(data.getIds());
        return entity;
    }
}
