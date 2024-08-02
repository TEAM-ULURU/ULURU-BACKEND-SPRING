package uluru.uluruspringbackend.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uluru.uluruspringbackend.common.CommonResponse;
import uluru.uluruspringbackend.common.utils.LongToStringMapper;
import uluru.uluruspringbackend.data.dao.MemberDAO;
import uluru.uluruspringbackend.data.dao.RoomDAO;
import uluru.uluruspringbackend.data.domain.Member;
import uluru.uluruspringbackend.data.domain.Room;
import uluru.uluruspringbackend.data.dto.room.RoomDTO;
import uluru.uluruspringbackend.repository.MemberRepository;
import uluru.uluruspringbackend.repository.RoomRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {

    private final MemberRepository memberRepository;
    private final RoomRepository roomRepository;
    private final RoomDAO roomDAO;
    private final LongToStringMapper longToStringMapper;

    @Transactional
    public CommonResponse createRoom(Long memberId) {

        log.info("[RoomService] [createRoom] memberId: {}", memberId);

        //룸 생성
        Room room = new Room();
        room = roomRepository.save(room);
        room.setRoomCode(longToStringMapper.longToString(room.getId()));
        log.info("[RoomService] [createRoom] room code: {}", room.getRoomCode());

        //멤버 추가
        Member roomOwner = memberRepository.findFirstById(memberId);
        if(roomOwner != null) {

            room.addMember(roomOwner);

            return new CommonResponse(true, HttpStatus.CREATED, "Room created successfully", getRoomInformation(memberId).getObject());
        }
        else{
            return new CommonResponse(false, HttpStatus.BAD_REQUEST, "로그인 되어있지 않거나 토큰이 없습니다.");
        }
    }

    public CommonResponse getRoomInformation(Long memberId) {

        log.info("[RoomService] [getRoomInformation] memberId: {}", memberId);
        RoomDTO roomDTO = roomDAO.getRoom(memberId);

        if(roomDTO == null) {
            return new CommonResponse(false, HttpStatus.NOT_FOUND, "member Or Room is not found");
        }

        return new CommonResponse(true, HttpStatus.OK, "Success get room Information", roomDTO);
    }

    //웹소켓???
    @Transactional
    public CommonResponse enterRoom(Long memberId, String roomCode) {

        log.info("[RoomService] [enterRoom] memberId: {}", memberId);
        Long roomId = longToStringMapper.stringToLong(roomCode);
        Optional<Room> byId = roomRepository.findById(roomId);

        if(byId.isPresent()) {

            Room room = byId.get();
            Member member = memberRepository.findFirstById(memberId);
            room.addMember(member);

            return new CommonResponse(true, HttpStatus.OK, "Enter Room Success", getRoomInformation(memberId).getObject());

        }
        else{
            return new CommonResponse(false, HttpStatus.NOT_FOUND, "member Or Room is not found");
        }

    }

    @Transactional
    public CommonResponse leaveRoom(Long memberId, String roomCode) {

        log.info("[RoomService] [leaveRoom] memberId: {}", memberId);
        Long roomId = longToStringMapper.stringToLong(roomCode);
        Optional<Room> byId = roomRepository.findById(roomId);

        if(byId.isPresent()) {

            Room room = byId.get();
            Member member = memberRepository.findFirstById(memberId);
            room.removeMember(member);

            return new CommonResponse(true, HttpStatus.OK, "Leave Room Success");

        }
        else{
            return new CommonResponse(false, HttpStatus.NOT_FOUND, "member Or Room is not found");
        }

    }

}
