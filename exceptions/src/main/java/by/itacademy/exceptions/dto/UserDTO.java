package by.itacademy.exceptions.dto;


import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private long dataCreate;
    private long dataUpdate;

    @JsonIgnore
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.getValue()));
    }
}

