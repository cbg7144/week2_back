package jpadb.demo.controller;

import jpadb.demo.model.UserInfo;
import jpadb.demo.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @ResponseBody
    @GetMapping("/userinfo/login")
    public boolean login(
            @RequestParam("id") String id,
            @RequestParam("pwd") String pwd) {
        UserInfo userInfo = userInfoRepository.findByUseridAndUserpwd(id, pwd);
        return userInfo != null;
    }

    @PostMapping("/userinfo/signup")
    public boolean signup(@RequestBody Map<String, String> body){
        UserInfo userInfo = userInfoRepository.findByUserid(body.get("id"));

        // 이미 존재하는 id인 경우 허용되지 않음
        if (userInfo != null) {
            return false;
        }

        // 존재하지 않는 id인 경우 저장하고 허용됨
        UserInfo newUser = new UserInfo();
        newUser.setId(body.get("id"));
        newUser.setPwd(body.get("pwd"));
        userInfoRepository.save(newUser);
        return true;

    }

//    @GetMapping("/userinfo/login")
//    public UserInfo login(
//            @RequestParam("id") String id,
//            @RequestParam("pwd") String pwd) {
//        return userInfoRepository.findByIdAndPwd(id, pwd);
//    }
}
