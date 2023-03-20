package project.goorm.authentication.business.core.domain.common.date;

import javax.persistence.Embeddable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class LastModifiedAt {

    private Instant lastModifiedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 common 외부 패키지에서 호출하지 말 것.
     */
    protected LastModifiedAt() {
        lastModifiedAt = null;
    }

    private LastModifiedAt(Instant lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public static LastModifiedAt initLastModifiedAt() {
        return new LastModifiedAt();
    }

    public static LastModifiedAt from(Instant instant) {
        return new LastModifiedAt(instant);
    }

    public Instant getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LastModifiedAt)) return false;
        LastModifiedAt that = (LastModifiedAt) o;
        return getLastModifiedAt().equals(that.getLastModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastModifiedAt());
    }

    @Override
    public String toString() {
        return lastModifiedAt.toString();
    }
}
