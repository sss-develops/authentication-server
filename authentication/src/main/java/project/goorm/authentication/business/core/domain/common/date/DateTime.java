package project.goorm.authentication.business.core.domain.common.date;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class DateTime {

    @Embedded
    private CreatedAt createdAt;

    @Embedded
    private LastModifiedAt lastModifiedAt;

    /**
     * @Nullary-Constructor. JPA 기본 생성자로 common 외부 패키지에서 호출하지 말 것.
     */
    protected DateTime() {
        this.createdAt = new CreatedAt();
        this.lastModifiedAt = new LastModifiedAt();
    }

    /**
     * 테스트를 제외한 common 외부 패키지에서 호출하지 말 것.
     */
    public DateTime(CreatedAt createdAt, LastModifiedAt lastModifiedAt) {
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public static DateTime initDateTime() {
        return new DateTime();
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public LastModifiedAt getLastModifiedAt() {
        return lastModifiedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DateTime)) return false;
        DateTime dateTime = (DateTime) o;
        return getCreatedAt().equals(dateTime.getCreatedAt()) && getLastModifiedAt().equals(dateTime.getLastModifiedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt(), getLastModifiedAt());
    }

    @Override
    public String toString() {
        return LocalDate.now().toString();
    }
}
