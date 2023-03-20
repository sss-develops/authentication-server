package project.goorm.authentication.test.helper.fixture;

import project.goorm.authentication.business.core.domain.member.entity.Member;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;

public class MemberFixture {

    public static Member createMember() {
        return new Member(
                LoginId.from("helloworld"),
                LoginPassword.from("Dkssudgktpdy1!"),
                Nickname.from("helloworld")
        );
    }

    public static LoginId getLoginId() {
        return LoginId.from("helloworld");
    }

    public static LoginPassword getPassword() {
        return LoginPassword.from("Dkssudgktpdy1!");
    }

    public static Nickname getNicknameAsValue() {
        return Nickname.from("helloworld");
    }

    public static String getNickname() {
        return Nickname.from("helloworld").getNickname();
    }
}
