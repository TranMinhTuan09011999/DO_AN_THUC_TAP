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
@Table(name="phong")
@ToString
public class Room {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MAPHONG", nullable = false)
    private Integer roomId;

    @Column(name = "TENPHONG", nullable = false)
    private String name;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Category> categories;
}
