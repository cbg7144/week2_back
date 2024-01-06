package jpadb.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KakaoResponseInfo {
    private String email;
    private String nickname;
    private String profileImage;
}
