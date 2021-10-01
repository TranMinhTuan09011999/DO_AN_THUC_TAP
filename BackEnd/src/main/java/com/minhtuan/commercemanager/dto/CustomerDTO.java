package com.minhtuan.commercemanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CustomerDTO {
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
    private Integer status;
}
