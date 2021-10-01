package com.minhtuan.commercemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeDTO {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstname;
    private String lastname;
    private Integer gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date birthday;
    private String address;
    private String phone;
    private String email;
}
