package com.reactboard.reactboard.entity;

import com.reactboard.reactboard.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int UserId;
    private String username;
    private String password;
    private String name;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private List<RoleRegister> roleRegisters;

    public List<SimpleGrantedAuthority> getAuthorities() {
        return roleRegisters.stream()
                .map(roleRegister ->
                        new SimpleGrantedAuthority(roleRegister.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    public PrincipalUser toPrincipalUser() {
        return PrincipalUser.builder()
                .userId(UserId)
                .username(username)
                .name(name)
                .email(email)
                .authorities(getAuthorities())
                .build();
    }
}
