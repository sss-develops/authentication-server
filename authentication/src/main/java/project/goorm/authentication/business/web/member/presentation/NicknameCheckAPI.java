package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.goorm.authentication.business.core.domain.member.entity.values.Nickname;
import project.goorm.authentication.business.web.member.application.SignupQuery;
import project.goorm.authentication.business.web.member.presentation.response.DuplicatedNicknameCheckResponse;
import project.goorm.authentication.common.annotation.helper.PresentationLayer;
import project.goorm.authentication.common.response.ApiResponse;

@PresentationLayer
@RequestMapping(path = "/api/members/signup")
public class NicknameCheckAPI {

    private final SignupQuery signupQuery;

    public NicknameCheckAPI(SignupQuery signupQuery) {
        this.signupQuery = signupQuery;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> validateDuplicatedNickname(@RequestParam String nickname) {
        DuplicatedNicknameCheckResponse data =
                signupQuery.validateDuplicatedNickname(Nickname.from(nickname));
        return ResponseEntity.ok()
                .body(ApiResponse.of(data));
    }
}
