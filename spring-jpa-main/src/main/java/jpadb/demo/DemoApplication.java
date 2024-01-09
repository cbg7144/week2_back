package jpadb.demo;

import jpadb.demo.api.ApiExplorer;
import jpadb.demo.model.Game;
import jpadb.demo.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		ApiExplorer apiExplorer = context.getBean(ApiExplorer.class);
		apiExplorer.runApiExplorer();
	}
	@Bean
	CommandLineRunner initDatabase(GameRepository gameRepository) {
		return args -> {
			// 더미 데이터 삽입 로직
//			gameRepository.save(new Game("0", "ㅋ", "콜", "http://file.koreafilm.or.kr/thm/02/00/05/47/tn_DPK015441.jpg"));
//			gameRepository.save(new Game("1", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));
//			gameRepository.save(new Game("2", "ㅁㄱㄷㅅ", "모가디슈", "http://file.koreafilm.or.kr/thm/02/99/17/33/tn_DPK017250.jpg"));
//			gameRepository.save(new Game("3", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));
//			gameRepository.save(new Game("4", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));
//			gameRepository.save(new Game("5", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));

		};
	}
}