package project.goorm.authentication.business.core.domain.member.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import project.goorm.authentication.business.core.domain.member.entity.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {
}
