package project.goorm.authentication.business.web.member.presentation.request;

public class LoginRequest {

    private String loginId;
    private String loginPassword;

    private LoginRequest() {
    }

    public LoginRequest(
            String loginId,
            String loginPassword
    ) {
        this.loginId = loginId;
        this.loginPassword = loginPassword;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    @Override
    public String toString() {
        return loginId;
    }
}
