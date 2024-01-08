package jpadb.demo.api;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jpadb.demo.model.MovieDao;
import jpadb.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class ApiExplorer {

    private static final StringBuilder cumulativeResponse = new StringBuilder();

    public static String getApiResponse(int startCount) throws IOException, InterruptedException {
        String baseUrl = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp";
        String serviceKey = "OG3E567G19R95KSI04UR";
        String collection = "kmdb_new2";
        String nation = URLEncoder.encode("대한민국", StandardCharsets.UTF_8);
        String url = String.format("%s?collection=%s&nation=%s&startCount=%d&listCount=10&ServiceKey=%s", baseUrl, collection, nation, startCount, serviceKey);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public void runApiExplorer() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(YourSpringConfig.class);
//        MovieDao movieDao = context.getBean(MovieDao.class);
//
//        ApiResponseHandler responseHandler = new ApiResponseHandler(movieDao);
        try {
            for (int i = 1; i <= 100; i++) {
                String pageResponse = getApiResponse(10*i);
                // pageResponse를 cumulativeResponse에 추가
                ApiResponseHandler.processPageResponse(pageResponse, cumulativeResponse);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("response: " + cumulativeResponse);
        //System.out.println("title: " + jsonResponse);
    }

    public static String getCumulativeResponse() {
        //System.out.println("jsonResponse: " + cumulativeResponse.toString());
        return cumulativeResponse.toString();
    }
}

@Component
class ApiResponseHandler {
    private static MovieDao movieDao; // Dependency injection of MovieDao
//
    @Autowired
    public ApiResponseHandler(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public static void processPageResponse(String pageResponse, StringBuilder cumulativeResponse) {
        int skippedMoviesCount = 0;
        try {
            JSONObject jsonObject = new JSONObject(pageResponse);
            JSONArray jsonArrayData = jsonObject.getJSONArray("Data");
            JSONObject jsonObjectData = jsonArrayData.getJSONObject(0);
            JSONArray jsonArrayResult = jsonObjectData.getJSONArray("Result");

            for (int i = 0; i < jsonArrayResult.length(); i++) {
                JSONObject jsonObjectMovie = jsonArrayResult.getJSONObject(i);

                // Extract required fields and check if they are not empty
                String docId = jsonObjectMovie.optString("DOCID");
                String title = jsonObjectMovie.optString("title");
                String rating = jsonObjectMovie.optString("rating");
                String runtime = jsonObjectMovie.optString("runtime");
                String genre = jsonObjectMovie.optString("genre");
                String repRlsDate = jsonObjectMovie.optString("repRlsDate");
                String posterUrl = jsonObjectMovie.optString("posters").split("\\|")[0];
                String stillUrl = jsonObjectMovie.optString("stlls").split("\\|")[0];

                // Skip if any required field is empty or rating is '미성년자관람불가'
                JSONArray directors = jsonObjectMovie.getJSONObject("directors").optJSONArray("director");
                String directorNm = (directors != null && directors.length() > 0) ? directors.getJSONObject(0).optString("directorNm") : "";

                if (docId.isEmpty() || title.isEmpty() || directorNm.isEmpty() || rating.isEmpty() || runtime.isEmpty() || genre.isEmpty() || repRlsDate.isEmpty() || posterUrl.isEmpty() || stillUrl.isEmpty() || "미성년자관람불가".equals(rating)) {
                    skippedMoviesCount++;
                    continue;
                }

                // Extract first two actors
                JSONArray actors = jsonObjectMovie.getJSONObject("actors").optJSONArray("actor");
                String actor1 = actors != null && actors.length() > 0 ? actors.getJSONObject(0).optString("actorNm") : "";
                String actor2 = actors != null && actors.length() > 1 ? actors.getJSONObject(1).optString("actorNm") : "";

                // Extract VOD URL
                JSONArray vods = jsonObjectMovie.getJSONObject("vods").optJSONArray("vod");
                String vodUrl = vods != null && vods.length() > 0 ? vods.getJSONObject(0).optString("vodUrl") : "";

                // Append extracted data to cumulativeResponse
                cumulativeResponse.append("DOCID: ").append(docId)
                        .append("\nTitle: ").append(title)
                        .append("\nDirector: ").append(directorNm)
                        .append("\nActor 1: ").append(actor1)
                        .append("\nActor 2: ").append(actor2)
                        .append("\nRuntime: ").append(runtime)
                        .append("\nRating: ").append(rating)
                        .append("\nGenre: ").append(genre)
                        .append("\nRelease Date: ").append(repRlsDate)
                        .append("\nPoster URL: ").append(posterUrl)
                        .append("\nStill URL: ").append(stillUrl)
                        .append("\nVOD URL: ").append(vodUrl)
                        .append("\n\n");

                Movie movie = new Movie();
                movie.setDocid(docId);
                movie.setTitle(title);
                movie.setDirectorNm(directorNm);
                movie.setActor1(actor1);
                movie.setActor2(actor2);
                movie.setRuntime(Integer.parseInt(runtime));
                movie.setRating(rating);
                movie.setGenre(genre);
                movie.setReRlsDate(repRlsDate);
                movie.setPosterUrl(posterUrl);
                movie.setStillUrl(stillUrl);
                movie.setVodUrl(vodUrl);
//
//                System.out.println("저장된 영화:");
                System.out.println("제목: " + movie.getTitle());
                System.out.println("감독: " + movie.getDirectorNm());

//                Movie movie = new Movie();
////
//                movie.setDocid("1000000L");
//                movie.setTitle("해리포터");
//                movie.setDirectorNm("홍길동");
//                movie.setActor1("홍길동");
//                movie.setActor2("abc");
//                movie.setRuntime(100);
//                movie.setRating("12세 관람가");
//                movie.setGenre("판타지");
//                movie.setReRlsDate("2023");
//                movie.setPosterUrl("https//");
//                movie.setStillUrl("https");
//                movie.setVodUrl("https");
//
                movieDao.save(movie);


            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error
        }
        //System.out.println("Number of skipped movies: " + skippedMoviesCount);  // Print the count of skipped movies

    }
}


//public class JsonParser {
//
//    public static void processPageResponse(String pageResponse, StringBuilder cumulativeResponse) {
//        try {
//            JSONObject jsonObject = new JSONObject(pageResponse);
//            JSONArray jsonArrayData = jsonObject.getJSONArray("Data");
//            JSONObject jsonObjectData = jsonArrayData.getJSONObject(0);
//            JSONArray jsonArrayResult = jsonObjectData.getJSONArray("Result");
//
//            for (int i = 0; i < jsonArrayResult.length(); i++) {
//                JSONObject jsonObjectMovie = jsonArrayResult.getJSONObject(i);
//
//                // Extract DOCID
//                String docId = jsonObjectMovie.getString("DOCID");
//
//                // Extract directorNm
//                JSONArray directors = jsonObjectMovie.getJSONObject("directors").getJSONArray("director");
//                String directorNm = directors.length() > 0 ? directors.getJSONObject(0).getString("directorNm") : "";
//
//                // Extract first two actors
//                JSONArray actors = jsonObjectMovie.getJSONObject("actors").getJSONArray("actor");
//                String actor1 = actors.length() > 0 ? actors.getJSONObject(0).getString("actorNm") : "";
//                String actor2 = actors.length() > 1 ? actors.getJSONObject(1).getString("actorNm") : "";
//
//                // Extract runtime, rating, genre, and repRlsDate
//                String runtime = jsonObjectMovie.getString("runtime");
//                String rating = jsonObjectMovie.getString("rating");
//                String genre = jsonObjectMovie.getString("genre");
//                String repRlsDate = jsonObjectMovie.getString("repRlsDate");
//
//                // Extract posterUrl, stillUrl, and vodUrl
//                String posterUrl = jsonObjectMovie.getString("posters").split("\\|")[0];
//                String stillUrl = jsonObjectMovie.getString("stlls").split("\\|")[0];
//                JSONArray vods = jsonObjectMovie.getJSONObject("vods").getJSONArray("vod");
//                String vodUrl = vods.length() > 0 ? vods.getJSONObject(0).getString("vodUrl") : "";
//
//                // Append extracted data to cumulativeResponse
//                cumulativeResponse.append("DOCID: ").append(docId)
//                        .append("\nDirector: ").append(directorNm)
//                        .append("\nActor 1: ").append(actor1)
//                        .append("\nActor 2: ").append(actor2)
//                        .append("\nRuntime: ").append(runtime)
//                        .append("\nRating: ").append(rating)
//                        .append("\nGenre: ").append(genre)
//                        .append("\nRelease Date: ").append(repRlsDate)
//                        .append("\nPoster URL: ").append(posterUrl)
//                        .append("\nStill URL: ").append(stillUrl)
//                        .append("\nVOD URL: ").append(vodUrl)
//                        .append("\n\n");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            // Handle JSON parsing error
//        }
//    }
//}

/*
Movie movie = new Movie();
        movie.setDocid(1000000L);
        movie.setTitle("해리포터");
        movie.setActorNm("abc");
        movie.setRuntime(100);
        movie.setRating("12세 관람가");
        movie.setGenre("판타지");
        movie.setPosterUrl("https//");

* */

//public class ApiResponseHandler {
//    public static void processPageResponse(String pageResponse, StringBuilder cumulativeResponse) {
//        try {
//            JSONObject jsonResponse = new JSONObject(pageResponse);
//            JSONArray data = jsonResponse.optJSONArray("Data"); // 'optJSONArray'는 null을 반환할 수 있음
//            if (data != null) {
//                for (int i = 0; i < data.length(); i++) {
//                    JSONObject movie = data.optJSONObject(i); // 'optJSONObject'는 null을 반환할 수 있음
//                    if (movie != null) {
//                        String title = movie.optString("title", "제목 없음"); // 'optString'은 기본값을 제공함
//                        cumulativeResponse.append(title).append("\n");
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//            // JSON 형식 또는 데이터 처리 관련 오류 처리
//        }
//    }
//}
