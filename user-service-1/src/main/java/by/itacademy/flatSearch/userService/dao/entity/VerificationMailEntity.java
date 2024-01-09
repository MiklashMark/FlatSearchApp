package by.itacademy.flatSearch.userService.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "users", name = "mail_queue")
public class VerificationMailEntity {
    @Id
    private UUID uuid;
    private String code;
    private String mail;
    @Column(name = "send_flag")
    private boolean sendFlag;

    public VerificationMailEntity(UUID uuid, String code, String mail, boolean isSended) {
        this.uuid = uuid;
        this.code = code;
        this.mail = mail;
        this.sendFlag = isSended;
    }

    public VerificationMailEntity() {
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

    public boolean isSended() {
        return sendFlag;
    }

    public void setSended(boolean sended) {
        sendFlag = sended;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationMailEntity that = (VerificationMailEntity) o;
        return sendFlag == that.sendFlag && Objects.equals(uuid, that.uuid) && Objects.equals(code, that.code) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, code, mail, sendFlag);
    }

    @Override
    public String toString() {
        return "VerificationUserEntity{" +
                "uuid=" + uuid +
                ", code='" + code + '\'' +
                ", mail='" + mail + '\'' +
                ", isSended=" + sendFlag +
                '}';
    }
}
