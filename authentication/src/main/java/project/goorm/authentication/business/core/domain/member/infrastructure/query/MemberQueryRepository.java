package project.goorm.authentication.business.core.domain.member.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import project.goorm.authentication.business.core.domain.common.deleted.Deleted;
import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse;

import java.util.Optional;

import static project.goorm.authentication.business.core.domain.member.entity.QMember.member;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public DuplicatedNicknameCheckResponse validateDuplicatedNickname(Nickname nickname) {
        Integer value = queryFactory.selectOne()
                .from(member)
                .where(
                        member.nickname.eq(nickname)
                                .and(member.deleted.eq(Deleted.FALSE))
                )
                .fetchFirst();
        return new DuplicatedNicknameCheckResponse(value);
    }

    public Optional<Member> findMemberByNickname(Nickname nickname) {
        return Optional.ofNullable(
                queryFactory.selectFrom(member)
                        .where(
                                member.nickname.eq(nickname)
                                        .and(member.deleted.eq(Deleted.FALSE))
                        )
                        .fetchOne()
        );
    }
}
