package com.minhtuan.commercemanager.message.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalResponse {
    private static final long serialVersionUID = 1L;
    private Double total;

    public TotalResponse(Object[] objects) {
        this.total = objects[0] != null ? Double.valueOf(objects[0].toString()) : null;
    }
}
