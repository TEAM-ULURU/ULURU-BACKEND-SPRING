package uluru.uluruspringbackend.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uluru.uluruspringbackend.common.response.CommonResponse;
import uluru.uluruspringbackend.common.response.LoginResponse;
import uluru.uluruspringbackend.common.security.token.JwtTokenProvider;
import uluru.uluruspringbackend.data.dao.MemberDAO;
import uluru.uluruspringbackend.data.dto.login.TokenDTO;
import uluru.uluruspringbackend.data.dto.member.*;
import uluru.uluruspringbackend.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberDAO memberDAO;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(MemberDTO member) {

        log.info("[UserService]-[login] 로그인 요청 : {}", member.getEmail());

        try {
            //Search User
            Optional<MemberDTO> userByEmail = memberDAO.getUserByEmail(member.getEmail());
            MemberDTO findUserDTO;

            //Search User Validation
            if(userByEmail.isPresent())
                //Success Search User
                findUserDTO = userByEmail.get();
            else{
                //Fail Search User
                log.info("[UserService]-[login] User not found : {}", member.getEmail());
                return new LoginResponse(false, HttpStatus.NOT_FOUND,"User not found",null, null);
            }


            //Generate Token
            TokenDTO loginToken = jwtTokenProvider.generateToken(findUserDTO);
            log.info("[UserService]-[login] 로그인 성공 : {}", jwtTokenProvider.getTokenInfo(loginToken.getAccessToken()));
            return new LoginResponse(true, HttpStatus.OK, "Login Success", loginToken, findUserDTO.isOauth());

        }catch (Exception e){
            log.info("[UserService]-[login] 로그인 도중 Exception 발생 \n {}", e.toString());
            return new LoginResponse(false, HttpStatus.INTERNAL_SERVER_ERROR,"Internal Server Error", null, null);
        }
    }

    public CommonResponse signup(MemberDTO memberDTO) {

        log.info("[UserService]-[signup] UserDAO에 회원 정보 저장 요청 : {}", memberDTO.getEmail());

        if(memberDAO.saveUser(memberDTO)){

            return new CommonResponse(true,HttpStatus.CREATED,"Signup Success");

        }
        else{
            return new CommonResponse(false, HttpStatus.CONFLICT,"User Already Exists");
        }

    }

    public boolean addInfo(MemberSignup1PageDTO memberSignup1PageDTO){
        return memberDAO.addUserInfo(memberSignup1PageDTO);
    }

    public boolean addInfo(MemberSignup2PageDTO memberSignup2PageDTO){
        return memberDAO.addUserInfo(memberSignup2PageDTO);
    }

    public boolean addInfo(MemberSignup3PageDTO memberSignup3PageDTO){
        return memberDAO.addUserInfo(memberSignup3PageDTO);
    }


    public CommonResponse getMyInfo(Long myId){
        MemberDTO me = memberDAO.getUserById(myId).get();

        MemberMyPageDTO memberMyPageDTO = MemberMyPageDTO.toMemberMyPageDTO(me);

        return new CommonResponse(true, HttpStatus.OK, "유저 정보 가져오기 성공", memberMyPageDTO);


    }

    public CommonResponse deleteUser(Long myId) {

        boolean isDeleteSuccess = memberDAO.deleteUser(myId);

        if(isDeleteSuccess) {
            return new CommonResponse(true, HttpStatus.OK, "유저 삭제 성공");
        }
        else
            return new CommonResponse(false, HttpStatus.BAD_REQUEST, "유저 삭제 실패");


    }

    public CommonResponse updateUser(Long myId, MemberUpdateDTO memberUpdateDTO) {

       MemberDTO me = memberDAO.getUserById(myId).get();

       if(!memberUpdateDTO.isUpdate(me))
           return new CommonResponse(false, HttpStatus.BAD_REQUEST, "유저 삭제 실패");

        boolean isUpdateSuccess = memberDAO.updateUser(myId, memberUpdateDTO);

        if(isUpdateSuccess)
            return new CommonResponse(true, HttpStatus.OK,"변경사항이 저장되었습니다.");
        else
            return new CommonResponse(false, HttpStatus.BAD_REQUEST,"로그인이 잘못 되었습니다.");

    }

/*
    @PostConstruct
    public void init1(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setName("한규현1");
        memberDTO.setEmail("abcde1@gmail.com");

        MemberDTO memberDTO1 = new MemberDTO();
        memberDTO1.setName("노민영1");
        memberDTO1.setEmail("qwere1@gmail.com");


        MemberDTO memberDTO2 = new MemberDTO();
        memberDTO2.setName("김다은1");
        memberDTO2.setEmail("qwerr1@gmail.com");

        signup(memberDTO);
        signup(memberDTO1);
        signup(memberDTO2);

    }

 */



}
