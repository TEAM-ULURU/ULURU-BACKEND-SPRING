package uluru.uluruspringbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uluru.uluruspringbackend.common.response.CommonResponse;
import uluru.uluruspringbackend.data.dto.member.MemberUpdateDTO;
import uluru.uluruspringbackend.service.MemberService;

@RestController
@RequestMapping("/api/my-page")
@RequiredArgsConstructor
public class MyPageController {

    private final MemberService memberService;

    @GetMapping("/get-info")
    public ResponseEntity<CommonResponse> getMyInfo() {

        CommonResponse response = memberService.getMyInfo(Long.parseLong(getUserIdFromContext()));
        return new ResponseEntity<>(response, response.getStatus());

    }

    @DeleteMapping("/delete")
    public ResponseEntity<CommonResponse> deleteUser() {
        CommonResponse response = memberService.deleteUser(Long.parseLong(getUserIdFromContext()));
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/update")
    public ResponseEntity<CommonResponse> updateUser(@RequestBody MemberUpdateDTO memberUpdateDTO) {
        CommonResponse response = memberService.updateUser(Long.parseLong(getUserIdFromContext()), memberUpdateDTO);
        return new ResponseEntity<>(response, response.getStatus());
    }

    private String getUserIdFromContext(){
        // SecurityContext에서 Authentication으로 UserID를 받아온다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
