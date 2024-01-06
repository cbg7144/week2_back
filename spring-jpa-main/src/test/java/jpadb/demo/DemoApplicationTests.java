package jpadb.demo;
import jpadb.demo.api.ApiExplorer;
import jpadb.demo.repository.MovieRepository;

import jpadb.demo.model.MovieDao;
import jpadb.demo.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private MovieDao movieDao; // MovieDao 인스턴스를 자동으로 주입

    @Test
    void apiDataToDatabaseTest() {
        ApiExplorer explorer = new ApiExplorer();
        explorer.main(new String[]{}); // ApiExplorer의 main 메소드를 실행하여 API 호출

        // DB에 저장된 데이터 확인
        Iterable<Movie> movies = movieDao.findAll(); // MovieDao를 사용하여 모든 영화 데이터 조회
        for (Movie movie : movies) {
            System.out.println(movie.getTitle()); // 각 영화의 제목 출력
            // 여기에 데이터가 올바른지 확인하기 위한 추가적인 검증 로직을 넣을 수 있음
        }
    }
}