package project.goorm.authentication.business.core.domain.common.date;

import javax.persistence.Embeddable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

@Embeddable
public class CreatedAt {

    private Instant createdAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 common 외부 패키지에서 호출하지 말 것.
     */
    protected CreatedAt() {
        createdAt = getInstant();
    }

    public static CreatedAt initCreatedAt() {
        return new CreatedAt();
    }

    private Instant getInstant() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(
                LocalDateTime.now(), ZoneId.of("Asia/Seoul")
        );
        return zonedDateTime.toInstant();
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatedAt)) return false;
        CreatedAt createdAt1 = (CreatedAt) o;
        return getCreatedAt().equals(createdAt1.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt());
    }

    @Override
    public String toString() {
        return createdAt.toString();
    }
}
