package jpadb.demo.api;

import jpadb.demo.api.ApiExplorer;
import jpadb.demo.model.Movie;
import jpadb.demo.model.MovieDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApiExplorerTests {

    @Autowired
    private MovieDao movieDao;

    @Test
    public void testMovieSaving() {
        ApiExplorer apiExplorer = new ApiExplorer();
        ApiResponseHandler responseHandler = new ApiResponseHandler(movieDao);

        try {
            // 가정: 한 페이지의 API 응답을 가져오기
            String pageResponse = apiExplorer.getApiResponse(1);

            // API 응답 처리 및 데이터베이스에 저장
            responseHandler.processPageResponse(pageResponse, new StringBuilder());

            // 데이터베이스에서 영화 정보 검증
            Iterable<Movie> movies = movieDao.findAll();
            assertTrue(movies.iterator().hasNext(), "영화 데이터가 데이터베이스에 저장되지 않았습니다.");

            Movie savedMovie = movies.iterator().next();
            assertNotNull(savedMovie.getTitle(), "저장된 영화의 제목이 null입니다.");
            assertNotNull(savedMovie.getDirectorNm(), "저장된 영화의 감독 이름이 null입니다.");

            // 추가적인 필드 검증 로직
            // 예: assertEquals("예상 영화 제목", savedMovie.getTitle());
        } catch (Exception e) {
            fail("API 응답 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
