package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="khachhang",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "EMAIL")})
@ToString
public class Customer extends Auditable<String>{
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "customerId is required")
    @Column(name = "MAKH", nullable = false)
    private String id;

    @NotBlank(message = "firstname is required")
    @Column(name = "TEN", nullable = false)
    private String firstname;

    @NotBlank(message = "lastname is required")
    @Column(name = "HO", nullable = false)
    private String lastname;

    @Column(name = "GIOITINH", nullable = false)
    private Integer gender;

    @Column(name = "NGAYSINH", nullable = false)
    private Date birthday;

    @NotBlank(message = "Email is required")
    @Column(name = "DIACHI", nullable = false)
    private String address;

    @NotBlank(message = "phone is required")
    @Column(name = "SDT", nullable = false)
    private String phone;

    @NotBlank(message = "Email is required")
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    public Customer(String id, String firstname, String lastname, Integer gender, Date birthday, String address, String phone, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
