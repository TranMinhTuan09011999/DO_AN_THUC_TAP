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
@Table(name="mau")
@ToString
public class Color {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @NotBlank(message = "colorId is required")
    @Column(name = "MAMAU", nullable = false)
    private String colorId;

    @NotBlank(message = "colorName is required")
    @Column(name = "TENMAU", nullable = false)
    private String colorName;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductDetail> productDetails;
}
