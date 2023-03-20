package project.goorm.authentication.business.web.member.presentation.request;

public class SignupRequest {

    private String loginId;
    private String password;
    private String nickname;

    private SignupRequest() {
    }

    public SignupRequest(
            String loginId,
            String password,
            String nickname
    ) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public String toString() {
        return String.format("LoginId: %s, NickName: %s", loginId, nickname);
    }
}
