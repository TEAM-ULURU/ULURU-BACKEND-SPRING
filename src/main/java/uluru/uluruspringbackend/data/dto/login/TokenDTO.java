package uluru.uluruspringbackend.data.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
