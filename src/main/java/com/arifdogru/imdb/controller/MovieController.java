package com.arifdogru.imdb.controller;

import com.arifdogru.imdb.entity.Movie;
import com.arifdogru.imdb.exception.ImdbException;
import com.arifdogru.imdb.pojo.NameBasic;
import com.arifdogru.imdb.repository.IMovieRepository;
import com.arifdogru.imdb.repository.INameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@EnableSwagger2
public class MovieController {

    private final String kevin = "Kevin Bacon";

    @Autowired
    private IMovieRepository movieRepository;

    @Autowired
    private INameRepository nameRepository;


    /**
     * URL /imdb/movies/{actor}
     * RequestMethod GET
     * @return List of all findByActor op. results
     */
    @RequestMapping(value = "/imdb/movies/{actorName}", method = RequestMethod.GET)
    public List<String> findAll(@PathVariable String actorName){
        log.info("FindAll started.");
        try {
            actorValidation(actorName);
        }
        catch (ImdbException e){
            log.error("Invalid actor name error!");
        }

        return movieRepository.findMovieNameByActorName(actorName);
    }

    /**
     * URL /imdb/movies/{actor}/{secondActorName}
     * RequestMethod GET
     * @return check both actor is in same movie ever results
     */
    @RequestMapping(value = "/imdb/sameMovies/{actorName}/{secondActorName}", method = RequestMethod.GET)
    public boolean  findSameAll(@PathVariable String actorName, @PathVariable String secondActorName){
        log.info("FindSameAll started.");

        return areTwoActorInSameMovie(actorName,secondActorName);

    }

    /**
     * URL /imdb/degree/{actor}
     * RequestMethod GET
     * @return Find degree of Kevin Bacon op. results
     */
    @RequestMapping(value = "/imdb/degree/{actorName}", method = RequestMethod.GET)
    public Integer findDegree(@PathVariable String actorName){
        log.info("FindDegree started.");
        int degree = 1;
        if (areTwoActorInSameMovie(kevin, actorName))
            return 1;

        List<String> allKevinBaconMovie = movieRepository.findMovieNameByActorName("Kevin Bacon");

        for (String movie: allKevinBaconMovie) {
            List<String> allActor = movieRepository.findAllActorInMovie(movie);

            for (String actor: allActor){
                areTwoActorInSameMovie(kevin, actor);
            }
            ++degree;

        }

        return degree;
    }

    /**
     * Validation of actor is in db
     * @param actorName
     * @return
     * @throws ImdbException
     */
    public boolean actorValidation(String actorName) throws ImdbException{
        log.info("Check actor name validation");
        NameBasic nameBasic = nameRepository.findByPrimaryname(actorName);
        if (!Objects.nonNull(nameBasic)){
            throw new ImdbException(actorName+" Validation error.");
        }
        return true;
    }

    /**
     * check two actor in same movie
     * @param actorFirst
     * @param actorSecond
     * @return
     */
    public boolean areTwoActorInSameMovie(String actorFirst, String actorSecond){
        List<String > sameMovieList = new ArrayList<>();
        List<String> firstActorsMovies = movieRepository.findMovieNameByActorName(actorFirst);
        List<String> secondActorsMovies = movieRepository.findMovieNameByActorName(actorSecond);

        for (String firstMovieName: firstActorsMovies) {
            for (String secondMovieName: secondActorsMovies) {
                if (firstMovieName.equals(secondMovieName))
                    sameMovieList.add(firstMovieName);
            }
        }

        return !sameMovieList.isEmpty();
    }

}
