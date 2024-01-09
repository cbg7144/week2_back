package jpadb.demo.repository;

import jpadb.demo.entity.Member;
import jpadb.demo.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, String> {

}
