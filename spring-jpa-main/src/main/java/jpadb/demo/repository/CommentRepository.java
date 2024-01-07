package jpadb.demo.repository;

import jpadb.demo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Member, Long> {

}
