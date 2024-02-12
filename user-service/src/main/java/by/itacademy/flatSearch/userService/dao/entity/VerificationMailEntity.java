package by.itacademy.flatSearch.userService.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "mail_verifications")
public class VerificationMailEntity {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String code;
    private String mail;

    public VerificationMailEntity() {
    }

    public VerificationMailEntity(UUID uuid, String code, String mail) {
        this.uuid = uuid;
        this.code = code;
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationMailEntity that = (VerificationMailEntity) o;
        return Objects.equals(code, that.code) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, mail);
    }

    @Override
    public String toString() {
        return "VerificationMailEntity{" +
                "code='" + code + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
