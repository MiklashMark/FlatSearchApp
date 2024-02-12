package by.itacademy.flatSearch.userService.dao.entity;


import by.itacademy.exceptions.enums.UserRole;
import by.itacademy.exceptions.enums.UserStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(schema = "users",  name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(unique = true)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;
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

    public User(UUID uuid,
                LocalDateTime dtCreate,
                LocalDateTime dtUpdate,
                String mail, String password,
                String fio, UserRole role,
                UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
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
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
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
        return dtCreate == user.dtCreate && dtUpdate == user.dtUpdate && Objects.equals(uuid, user.uuid) && Objects.equals(mail, user.mail) && Objects.equals(fio, user.fio) && role == user.role && status == user.status && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "uuid='" + uuid + '\'' +
                ", dt_create=" + dtCreate +
                ", dt_update=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
