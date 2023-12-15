package by.itacademy.jd2.MkJD210323.flatSearch.userService.dao.entity;

import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserRole;
import by.itacademy.jd2.MkJD210323.flatSearch.userService.core.enums.UserStatus;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "client",  name = "client")
public class User {
    @Id
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private long dataCreate;
    @Column(name = "dt_update", nullable = false)
    private long dataUpdate;
    @Column(nullable = false)
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

    public User(UUID uuid, long dt_create,
                long dt_update, String mail,
                String fio, UserRole role,
                UserStatus status, java.lang.String password) {

        this.uuid = uuid;
        this.dataCreate = dt_create;
        this.dataUpdate = dt_update;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public java.lang.String getPassword() {
        return password;
    }

    public void setPassword(java.lang.String password) {
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

    public void setMail(java.lang.String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(java.lang.String fio) {
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
