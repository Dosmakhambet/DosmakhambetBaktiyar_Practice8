package com.dosmakhambetbbaktiyar_practice8.model;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Event> events;

    @Data
    public static class SecurityUser implements UserDetails {
        private final String username;
        private final String password;
        private final List<SimpleGrantedAuthority> authorities;
        private final boolean isActive;

        public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorities, boolean isActive) {
            this.username = username;
            this.password = password;
            this.authorities = authorities;
            this.isActive = isActive;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return isActive;
        }

        @Override
        public boolean isAccountNonLocked() {
            return isActive;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return isActive;
        }

        @Override
        public boolean isEnabled() {
            return isActive;
        }

        public static UserDetails fromUser(User user){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPassword(),
                    user.getStatus().equals(Status.ACTIVE),
                    user.getStatus().equals(Status.ACTIVE),
                    user.getStatus().equals(Status.ACTIVE),
                    user.getStatus().equals(Status.ACTIVE),
                    user.getRole().grantedAuthorities()
            );
        }
    }
}
