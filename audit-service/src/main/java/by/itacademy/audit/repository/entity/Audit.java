package by.itacademy.audit.repository.entity;


import by.itacademy.exceptions.enums.action.EssenceType;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "audit", name = "audit")
public class Audit {

    @GeneratedValue
    @Id
    @Column(nullable = false, unique = true)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false)
    private LocalDateTime dtCreate;
    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EssenceType essenceType;
    @Column(name = "essence_id")
    private String essenceId;


    public Audit() {
    }

    public Audit(UUID uuid, LocalDateTime dtCreate,
                 User user, String text,
                 EssenceType essenceType, String essenceId) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.essenceType = essenceType;
        this.essenceId = essenceId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getEssenceType() {
        return essenceType;
    }

    public void setEssenceType(EssenceType essenceType) {
        this.essenceType = essenceType;
    }

    public String getEssenceId() {
        return essenceId;
    }

    public void setEssenceId(String essenceId) {
        this.essenceId = essenceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audit audit = (Audit) o;
        return Objects.equals(uuid, audit.uuid) && Objects.equals(dtCreate, audit.dtCreate) && Objects.equals(user, audit.user) && Objects.equals(text, audit.text) && essenceType == audit.essenceType && Objects.equals(essenceId, audit.essenceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, user, text, essenceType, essenceId);
    }

    @Override
    public String toString() {
        return "Audit{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", essenceType=" + essenceType +
                ", essenceId='" + essenceId + '\'' +
                '}';
    }
}
