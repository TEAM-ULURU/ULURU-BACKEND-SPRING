package uluru.uluruspringbackend.common.utils;

import org.hashids.Hashids;
import org.springframework.stereotype.Component;

@Component
public class LongToStringMapper {

    // 솔트 값과 최소 길이를 설정
    private final Hashids hashids = new Hashids("this is my salt", 8);

    // long 값을 문자열로 변환
    public String longToString(long value) {
        return hashids.encode(value);
    }

    // 문자열을 long 값으로 복원
    public long stringToLong(String str) {
        long[] decoded = hashids.decode(str);
        if (decoded.length > 0) {
            return decoded[0];
        } else {
            throw new IllegalArgumentException("Invalid hashid string");
        }
    }

}


