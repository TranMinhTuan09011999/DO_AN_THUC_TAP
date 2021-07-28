package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="phieudat")
@ToString
public class Order {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "orderId is required")
    @Column(name = "phieudat", nullable = false)
    private String orderId;

    @NotBlank(message = "dateOfOrder is required")
    @Column(name = "NGAYDAT", nullable = false)
    private Date dateOfOrder;

    @NotBlank(message = "firstnameOfReveiver is required")
    @Column(name = "TEN", nullable = false)
    private String firstnameOfReveiver;

    @NotBlank(message = "lastnameOfReveiver is required")
    @Column(name = "HO", nullable = false)
    private String lastnameOfReveiver;

    @NotBlank(message = "addressOfReceiver is required")
    @Column(name = "DIACHI", nullable = false)
    private String addressOfReceiver;

    @NotBlank(message = "phoneOfReceiver is required")
    @Column(name = "SDT", nullable = false)
    private String phoneOfReceiver;

    @NotBlank(message = "deliveryDate is required")
    @Column(name = "NGAYGIAO", nullable = false)
    private Date deliveryDate;

    @NotBlank(message = "status is required")
    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;
}
