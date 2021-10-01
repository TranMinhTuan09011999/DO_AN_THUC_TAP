package com.minhtuan.commercemanager.model;

import com.minhtuan.commercemanager.validation.email.ValidEmail;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="taikhoan")
@ToString
public class Account extends Auditable<String>{
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "Email is required")
    @ValidEmail
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "reset_token")
    private String resetToken;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANQ", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;
}
