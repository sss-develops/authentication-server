package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginId;
import project.goorm.authentication.business.core.domain.member.entity.values.LoginPassword;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.web.member.application.SignupCommand;
import project.goorm.authentication.business.web.member.presentation.request.SignupRequest;
import project.goorm.authentication.common.annotation.helper.PresentationLayer;
import project.goorm.authentication.common.response.ApiResponse;

import static org.springframework.http.HttpStatus.CREATED;

@PresentationLayer
@RequestMapping(path = "/api/members/signup")
public class SignupAPI {

    private final SignupCommand signupCommand;

    public SignupAPI(SignupCommand signupCommand) {
        this.signupCommand = signupCommand;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> signup(@RequestBody SignupRequest request) {
        Long memberId = signupCommand.signup(
                LoginId.from(request.getLoginId()),
                LoginPassword.from(request.getPassword()),
                Nickname.from(request.getNickname())
        );
        return ResponseEntity.status(CREATED)
                .body(ApiResponse.of(CREATED, memberId));
    }
}
