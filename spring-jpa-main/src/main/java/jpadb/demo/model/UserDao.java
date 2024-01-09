//package jpadb.demo.model;
//
//import jpadb.demo.model.User;
//import jpadb.demo.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.util.Streamable;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDao { // auto-writing / Data Access Object
//    @Autowired
//    private UserRepository repository;
//
//
//    public User save(User user){
//        repository.save(user); // repository 함수가 다 자동으로 해줌
//        return user;
//    }
//
//    public List<User> getAllUsers(){
//        List<User> users=new ArrayList<>();
//        Streamable.of(repository.findAll()).
//                forEach(users::add);
//
//        return users;
//    }
//
//    public void delete(User user){
//        repository.delete(user);
//    }
//
//    public void deleteById(Long id){
//        repository.deleteById(id);
//    }
//}