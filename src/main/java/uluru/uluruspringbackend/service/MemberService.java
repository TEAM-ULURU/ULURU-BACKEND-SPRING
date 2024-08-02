package uluru.uluruspringbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uluru.uluruspringbackend.common.CommonResponse;
import uluru.uluruspringbackend.common.LoginResponse;
import uluru.uluruspringbackend.common.security.token.JwtTokenProvider;
import uluru.uluruspringbackend.data.dao.MemberDAO;
import uluru.uluruspringbackend.data.dto.login.TokenDTO;
import uluru.uluruspringbackend.data.dto.member.MemberDTO;
import uluru.uluruspringbackend.data.dto.member.MemberSignup1PageDTO;
import uluru.uluruspringbackend.data.dto.member.MemberSignup2PageDTO;
import uluru.uluruspringbackend.data.dto.member.MemberSignup3PageDTO;

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




}
