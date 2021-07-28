package com.minhtuan.commercemanager.dto;

import com.minhtuan.commercemanager.model.Room;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private static final long serialVersionUID = 1L;

    private String categoryId;
    private String categoryName;
    private RoomDTO room;
}
