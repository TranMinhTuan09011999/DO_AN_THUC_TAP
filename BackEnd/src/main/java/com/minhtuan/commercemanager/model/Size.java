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
@Table(name="kichthuoc")
@ToString
public class Size {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "sizeId is required")
    @Column(name = "MAKT", nullable = false)
    private Integer sizeId;

    @NotBlank(message = "size is required")
    @Column(name = "TENKT", nullable = false)
    private String size;

    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductDetail> productDetails;
}
