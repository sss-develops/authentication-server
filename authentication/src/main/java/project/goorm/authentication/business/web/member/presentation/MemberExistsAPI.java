package project.goorm.authentication.business.web.member.presentation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.goorm.authentication.business.web.member.application.service.MemberExistsService;

@RestController
@RequestMapping("/api/member/exist")
public class MemberExistsAPI {

    private final MemberExistsService memberExistsService;

    public MemberExistsAPI(MemberExistsService memberExistsService) {
        this.memberExistsService = memberExistsService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<Boolean> memberExistsByMemberId(@PathVariable(value = "memberId") Long memberId) {
        Boolean exists = memberExistsService.memberExistsByMemberId(memberId);
        return ResponseEntity.ok().body(exists);
    }
}
