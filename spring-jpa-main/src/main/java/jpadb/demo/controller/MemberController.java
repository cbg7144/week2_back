package jpadb.demo.controller;

import jpadb.demo.dto.KakaoResponseInfo;
import jpadb.demo.dto.KakaoUserInfo;
import jpadb.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
 // 컨트롤러 빈으로 등록
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    // menu.do를 클릭하면 views/member/login.jsp로 이동
    @PostMapping("/login/do")
    public ResponseEntity<KakaoResponseInfo> login(@RequestBody Map<String, String> body) {

        // token을 받아서 처리
        KakaoUserInfo userInfo = memberService.getKakaoInfoByToken(body.get("token"));
        KakaoResponseInfo responseInfo = memberService.getKakaoResponseInfo(userInfo.getKakao_account());
        return new ResponseEntity<>(responseInfo, HttpStatus.OK);
    }


}