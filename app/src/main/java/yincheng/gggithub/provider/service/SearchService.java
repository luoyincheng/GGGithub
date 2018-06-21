package yincheng.gggithub.provider.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import yincheng.gggithub.mvp.model.Code;
import yincheng.gggithub.mvp.model.GithubUser;
import yincheng.gggithub.mvp.model.Issue;
import yincheng.gggithub.mvp.model.Repo;
import yincheng.gggithub.provider.network.Pageable;

/**
 * Created by yincheng on 2018/6/4/18:07.
 * github:luoyincheng
 */
public interface SearchService {
   @GET("search/{searchType}")
   Observable<Pageable<Repo>> searchRepositories(@Path("searchType") String searchType, @Query
         (value = "q",
               encoded = true) String query, @Query("page") long page);// TODO: 2018/6/5

   @GET("search/repositories}")
   Observable<Pageable<Repo>> searchRepositories(
         @Query(value = "q", encoded = true) String query,
         @Query("page") long page);

   @GET("search/users}")
   Observable<Pageable<GithubUser>> searchUsers(
         @Query(value = "q", encoded = true) String query,
         @Query("page") long page);

   @GET("search/issues}")
   Observable<Pageable<Issue>> searchIssues(
         @Query(value = "q", encoded = true) String query,
         @Query("page") long page);

   @GET("search/code}")
   Observable<Pageable<Code>> searchCode(
         @Query(value = "q", encoded = true) String query,
         @Query("page") long page);
}
