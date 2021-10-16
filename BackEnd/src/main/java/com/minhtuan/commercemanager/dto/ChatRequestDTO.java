package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
@ToString
public class ChatRequestDTO {
    private String message;
    private String userId;
    private String toUserId;
    private String employeeId;
    private Date time;
}
