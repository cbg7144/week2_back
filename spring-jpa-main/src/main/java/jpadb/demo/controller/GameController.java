package jpadb.demo.controller;

import jpadb.demo.model.Game;
import jpadb.demo.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @GetMapping("/game/id")
    public Game giveGameIfo(@RequestParam Map<String, String> body){

        return gameService.getGameByGameId(body.get("id"));
    }
}
