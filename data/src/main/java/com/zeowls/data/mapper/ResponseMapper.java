package com.zeowls.data.mapper;

import com.zeowls.data.entity.Response_data;
import com.zeowls.domain.entity.Response;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class ResponseMapper extends Mapper<Response, Response_data> {
    @Inject
    public ResponseMapper() {
    }

    @Override
    public Response map(Response_data data) {
        if (data == null) return null;
        Response entity = new Response();
        entity.setError(data.getError());
        entity.setMessage(data.getMessage());
        entity.setMessageCode(data.getMessageCode());
        entity.setResponse(data.getResponse());
        return entity;
    }

    @Override
    public Response_data reverse(Response brand_) {
        return null;
    }
}
