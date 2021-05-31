package com.arifdogru.imdb.repository;

import com.arifdogru.imdb.entity.Movie;
import com.arifdogru.imdb.pojo.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IMovieRepository extends JpaRepository<Movie,Long> {

    @Query(value="select tb.original_title from title_basic tb , title_principals tp ,name_basic nb " +
            "where nb.nconst = tp.nconst and tp.tconst = tb.tconst and " +
            "nb.primaryname like '%:actorName%'",nativeQuery = true)
    public List<String> findMovieNameByActorName(String actorName);

    @Query(value = "select tb.*, tp.* from title_basic tb , title_principals tp ,name_basic nb " +
            "where nb.nconst = tp.nconst and tp.tconst = tb.tconst and " +
            "nb.primaryname like + '%:actorName%'", nativeQuery = true)
    List<Object[]> findAllMoviesByActorName(@Param("actorName") String actorName);

    @Query(value = "select nb.primaryname from title_basic tb, name_basic nb, title_principals tp"+
            " where tb.tconst = tp.tconst and nb.nconst = tp.nconst and tb.primarytitle like '%movieName%'", nativeQuery = true)
    List<String> findAllActorInMovie(@Param("movieName") String movieName);
}
