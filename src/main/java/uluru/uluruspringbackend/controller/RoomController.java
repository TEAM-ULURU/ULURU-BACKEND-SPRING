package uluru.uluruspringbackend.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uluru.uluruspringbackend.common.CommonResponse;
import uluru.uluruspringbackend.service.RoomService;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createRoom() {

        String memberIdFromContext = getUserIdFromContext();
        CommonResponse response = roomService.createRoom(Long.parseLong(memberIdFromContext));

        return new ResponseEntity<>(response, response.getStatus());

    }

    @GetMapping("/get-info")
    public ResponseEntity<CommonResponse> getRoomInformation() {

        log.info("[RoomController] [getRoomInformation]");

        String memberIdFromContext = getUserIdFromContext();
        CommonResponse response = roomService.getRoomInformation(Long.parseLong(memberIdFromContext));

        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/enter")
    public ResponseEntity<CommonResponse> enterRoom(@RequestBody String roomCode) {

        log.info("[RoomController] [enterRoom] roomCode: {}", roomCode);

        String memberIdFromContext = getUserIdFromContext();
        CommonResponse response = roomService.enterRoom(Long.parseLong(memberIdFromContext), roomCode);

        return new ResponseEntity<>(response, response.getStatus());
    }

    @PostMapping("/leave")
    public ResponseEntity<CommonResponse> leaveRoom(@RequestParam String roomCode) {

        log.info("[RoomController] [leaveRoom] roomCode: {}" , roomCode);

        String memberIdFromContext = getUserIdFromContext();
        CommonResponse response = roomService.leaveRoom(Long.parseLong(memberIdFromContext), roomCode);

        return new ResponseEntity<>(response, response.getStatus());
    }


;
    private String getUserIdFromContext(){
        // SecurityContext에서 Authentication으로 UserID를 받아온다
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (String) authentication.getPrincipal();
    }

}
