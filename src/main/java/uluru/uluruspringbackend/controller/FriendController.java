package uluru.uluruspringbackend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uluru.uluruspringbackend.common.response.CommonResponse;
import uluru.uluruspringbackend.common.response.FriendPageInfoResponse;
import uluru.uluruspringbackend.service.FriendService;

@RestController
@RequestMapping("/api/friend")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    @PostMapping("/add")
    public ResponseEntity<FriendPageInfoResponse> addFriend(@RequestParam String friendEmail){

        FriendPageInfoResponse friendPageInfoResponse = friendService.addFriend(friendEmail, Long.parseLong(getUserIdFromContext()));

        return ResponseEntity.status(friendPageInfoResponse.getStatus()).body(friendPageInfoResponse);
    }

    @GetMapping("/get-info")
    public ResponseEntity<FriendPageInfoResponse> getFriendInfo(){

        FriendPageInfoResponse friendPageInfoResponse = friendService.getFriend(Long.parseLong(getUserIdFromContext()));

        return ResponseEntity.status(friendPageInfoResponse.getStatus()).body(friendPageInfoResponse);
    }

    private String getUserIdFromContext(){
        // SecurityContext에서 Authentication으로 UserID를 받아온다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }
}
