package com.minhtuan.commercemanager.message.request;

import com.minhtuan.commercemanager.model.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryRequest {
    private static final long serialVersionUID = 1L;

    private String categoryId;
    private String categoryName;
    private Integer room;
}
