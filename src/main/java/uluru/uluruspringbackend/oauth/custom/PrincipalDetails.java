package uluru.uluruspringbackend.oauth.custom;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import uluru.uluruspringbackend.data.dto.member.MemberDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PrincipalDetails implements OAuth2User, UserDetails {

    private MemberDTO memberDTO;
    private Map<String, Object> attributes = new HashMap<>();

    public PrincipalDetails(MemberDTO memberDTO, Map<String, Object> attributes) {
        this.memberDTO = memberDTO;
        this.attributes = attributes;

    }

    //user 이름 가져오기
    @Override
    public String getName() {
        return memberDTO.getName();
    }

    //third-party 에서 가져온 유저 정보 Map 가져오기
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {

        return memberDTO.getName();

    }

    public MemberDTO getUserDTO() {
        return memberDTO;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
