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
@Table(name="danhmuchang")
@ToString
public class Category {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "CategoryId is required")
    @Column(name = "MADMH", nullable = false)
    private String categoryId;

    @NotBlank(message = "Category name is required")
    @Column(name = "TENDMH", nullable = false)
    private String categoryName;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPHONG")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Room room;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;
}
