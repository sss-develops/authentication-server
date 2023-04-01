package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.goorm.authentication.business.web.member.application.service.MemberExistsService;

@RestController
@RequestMapping("/api/member/exist")
public class MemberExistsAPI {

    private final MemberExistsService memberExistsService;

    public MemberExistsAPI(MemberExistsService memberExistsService) {
        this.memberExistsService = memberExistsService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Boolean> memberExistsByMemberId(@RequestParam("memberId") Long memberId) {
        Boolean exists = memberExistsService.memberExistsByMemberId(memberId);
        return ResponseEntity.ok().body(exists);
    }
}
