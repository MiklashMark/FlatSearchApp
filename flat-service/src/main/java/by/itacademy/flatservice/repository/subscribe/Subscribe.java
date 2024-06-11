package by.itacademy.flatservice.repository.subscribe;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "flat", name = "subscribe")
public class Subscribe {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "user_uuid")
    private UUID userUuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscribe_uuid")
    private List<SubscribeFilter> subscribeFilters;

    public Subscribe() {
    }

    public Subscribe( UUID userUuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, List<SubscribeFilter> subscribeFilters) {
        this.userUuid = userUuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.subscribeFilters = subscribeFilters;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public List<SubscribeFilter> getSubscribeFilters() {
        return subscribeFilters;
    }

    public void setSubscribeFilters(SubscribeFilter subscribeFilter) {
        if (subscribeFilters == null) {
            subscribeFilters = new ArrayList<>();
        }
        subscribeFilters.add(subscribeFilter);
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

    public void setSubscribeFilters(List<SubscribeFilter> subscribeFilters) {
        this.subscribeFilters = subscribeFilters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscribe subscribe = (Subscribe) o;
        return Objects.equals(uuid, subscribe.uuid) && Objects.equals(userUuid, subscribe.userUuid) && Objects.equals(dtCreate, subscribe.dtCreate) && Objects.equals(dtUpdate, subscribe.dtUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, userUuid, dtCreate, dtUpdate);
    }


}

