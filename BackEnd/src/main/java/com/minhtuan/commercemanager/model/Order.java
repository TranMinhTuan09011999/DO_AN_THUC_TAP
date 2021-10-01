package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
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
    @Column(name = "MAPD", nullable = false)
    private String orderId;

    @Column(name = "NGAYDAT", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
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

    @Column(name = "NGAYGIAO")
    private Date deliveryDate;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @Column(name = "THANHTOAN", nullable = false)
    private Integer payment;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANVGH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee deliveryEmployee;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKH", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "orderInvoice", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Invoice> invoices;

}
