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
			gameRepository.save(new Game("0", "ㅋ", "콜", "http://file.koreafilm.or.kr/thm/02/00/05/47/tn_DPK015441.jpg"));
			gameRepository.save(new Game("1", "ㅂㅈㄷㅅ2", "범죄도시2", "http://file.koreafilm.or.kr/thm/02/99/17/68/tn_DPK019003.jpg"));
			gameRepository.save(new Game("2", "ㅁㄱㄷㅅ", "모가디슈", "http://file.koreafilm.or.kr/thm/02/99/17/33/tn_DPK017250.jpg"));
			gameRepository.save(new Game("3", "ㅅㅂ", "서복", "http://file.koreafilm.or.kr/thm/02/99/17/12/tn_DPK016544.jpg"));
			gameRepository.save(new Game("4", "ㅅㄱㅎㄲ-ㅇㄱ ㅇ", "신과함께-인과 연", "http://file.koreafilm.or.kr/thm/02/00/04/57/tn_DPK013042.jpg"));
			gameRepository.save(new Game("5", "ㄱㅅㅈㄷ", "기술자들", "http://file.koreafilm.or.kr/thm/02/00/04/57/tn_DPK013040.jpg"));
			gameRepository.save(new Game("6", "ㄷㄹ", "드림", "http://file.koreafilm.or.kr/thm/02/99/18/01/tn_DPK020414.jpg"));


		};
	}
}