package jpadb.demo.controller;


import jpadb.demo.model.User;
import jpadb.demo.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

    @Autowired
    private UserDao userDao;


    @GetMapping("/user/get-all") // 나중에 android에서 요청할 때
    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    // android에서 새로운 정보를 입력받는다면
    @PostMapping("/user/save")
    public User save(@RequestBody User user){ // @RequestBody로 data 받아옴
        return userDao.save(user);
    }

    //@PostMapping("/user/search")


}