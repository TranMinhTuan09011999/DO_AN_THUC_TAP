package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="nhacungcap")
@ToString
public class Provider {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "providerId is required")
    @Column(name = "MANCC", nullable = false)
    private String providerId;

    @NotBlank(message = "providerName is required")
    @Column(name = "TENNCC", nullable = false)
    private String providerName;

    @NotBlank(message = "providerAddress is required")
    @Column(name = "DIACHI", nullable = false)
    private String providerAddress;

    @NotBlank(message = "providerEmail is required")
    @Column(name = "EMAIL", nullable = false)
    private String providerEmail;

    @NotBlank(message = "providerPhone is required")
    @Column(name = "SDT", nullable = false)
    private String providerPhone;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;
}
