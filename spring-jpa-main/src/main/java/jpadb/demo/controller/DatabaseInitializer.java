//package jpadb.demo.controller;
//
//import jpadb.demo.model.Game;
//import jpadb.demo.repository.GameRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DatabaseInitializer implements CommandLineRunner {
//
//    private final GameRepository gameRepository;
//
//    @Autowired
//    public DatabaseInitializer(GameRepository gameRepository) {
//        this.gameRepository = gameRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//        gameRepository.save(new Game("0", "ㅋ", "콜", "http://file.koreafilm.or.kr/thm/02/00/05/47/tn_DPK015441.jpg"));
//			gameRepository.save(new Game("1", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));
//			gameRepository.save(new Game("2", "ㅁㄱㄷㅅ", "모가디슈", "http://file.koreafilm.or.kr/thm/02/99/17/33/tn_DPK017250.jpg"));
//    }
//}