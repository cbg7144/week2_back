package jpadb.demo.service;

import jakarta.transaction.Transactional;
import jpadb.demo.model.Game;
import jpadb.demo.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    public Game getGameByGameId(String gameid) {
        Optional<Game> game = gameRepository.findById(gameid);
        return game.orElse(null); // 게임이 존재하지 않는 경우 null 반환
    }
}
