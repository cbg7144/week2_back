package jpadb.demo.repository;

import jpadb.demo.model.Movie;
import jpadb.demo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
    UserInfo findByUseridAndUserpwd(String id, String pwd);
    <Optional> UserInfo findByUserid(String id);
}
