package by.itacademy.flatSearch.userService.dao.entity;

import by.itacademy.flatSearch.userService.core.enums.UserRole;
import by.itacademy.flatSearch.userService.core.enums.UserStatus;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(schema = "users",  name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private long dataCreate;
    @Column(name = "dt_update", nullable = false)
    private long dataUpdate;
    @Column(nullable = false, unique = true)
    private String mail;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    public User() {
    }

    public User(UUID uuid, long dataCreate,
                long dataUpdate, String mail,
                String password, String fio,
                UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.dataCreate = dataCreate;
        this.dataUpdate = dataUpdate;
        this.mail = mail;
        this.password = password;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public long getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(long dataCreate) {
        this.dataCreate = dataCreate;
    }

    public long getDataUpdate() {
        return dataUpdate;
    }

    public void setDataUpdate(long dataUpdate) {
        this.dataUpdate = dataUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return dataCreate == user.dataCreate && dataUpdate == user.dataUpdate && Objects.equals(uuid, user.uuid) && Objects.equals(mail, user.mail) && Objects.equals(fio, user.fio) && role == user.role && status == user.status && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dataCreate, dataUpdate, mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", dt_create=" + dataCreate +
                ", dt_update=" + dataUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
