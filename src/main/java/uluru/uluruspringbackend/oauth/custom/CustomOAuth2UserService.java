package uluru.uluruspringbackend.oauth.custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import uluru.uluruspringbackend.data.dto.member.MemberDTO;
import uluru.uluruspringbackend.oauth.OAuth2UserInfoMaker;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    /**
     *
     * @description: Security 에서 받은 유저 정보를 파싱하여 PrincipalDetails 클래스로 반환하는 매소드
     */

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // 1. 유저 정보(attributes) 가져오기 (Json 형태, third-party 마다 형식이 다름)
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();
        log.info("[CustomOAuth2UserService]-[loadUser] OAuth 로그인 성공");

        // 2. resistrationId 가져오기 (third-party id)
        // ex) google, kakao, naver
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        log.info("[CustomOAuth2UserService]-[loadUser] registrationID: {}", registrationId);


        // 3.OAuth 유저 정보 생성
        // third-party 마다 다르므로 OAuth2UserInfo class에서 처리
        OAuth2UserInfoMaker oAuth2UserInfoMaker = OAuth2UserInfoMaker.of(registrationId, oAuth2UserAttributes);
        log.info("[CustomOAuth2UserService]-[loadUser] userName: {}, userEmail: {}", oAuth2UserInfoMaker.getName(), oAuth2UserInfoMaker.getEmail());

        // 4. OAuth 유저 정보를 이용하여 UserDTO 생성
        MemberDTO OAuthLoginUserDTO = getUserDTO(oAuth2UserInfoMaker, registrationId);
        log.info("[CustomOAuth2UserService]-[loadUser] UserDTO 생성");

        // 5. PrincipalDetails (extends OAuth2User)로 반환
        return new PrincipalDetails(OAuthLoginUserDTO, oAuth2UserAttributes);

    }


    /**
     *
     * @description: 파싱한 유저 정보를 통해 UserDTO를 생성한다.
     */
    private static MemberDTO getUserDTO(OAuth2UserInfoMaker oAuth2UserInfoMaker, String registrationId) {

        MemberDTO dto = new MemberDTO();
        dto.setId(null);
        dto.setName(oAuth2UserInfoMaker.getName());
        dto.setEmail(oAuth2UserInfoMaker.getEmail());
        dto.setOauth(false);
        return dto;

    }
}
