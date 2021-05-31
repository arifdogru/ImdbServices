package com.arifdogru.imdb;

import com.arifdogru.imdb.controller.MovieController;
import com.arifdogru.imdb.pojo.NameBasic;
import com.arifdogru.imdb.repository.IMovieRepository;
import com.arifdogru.imdb.repository.INameRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.booleanThat;

@SpringBootTest
@MockitoSettings
@ExtendWith(MockitoExtension.class)
class ImdbApplicationTests {


    @InjectMocks
    private MovieController movieController;

    @Mock
    private IMovieRepository movieRepository;

    @Mock
    INameRepository nameRepository;


    @Test
    void contextLoads() {
    }

    @Test
    void sameMovieTest(){
        List<String> movieList = new ArrayList<>();
        List<String> actorList = new ArrayList<>();
        movieList.add("The Lord Of The Rings");
        actorList.add("Elijah Wood");
        actorList.add("Orlando Bloom");


        Mockito.when(movieRepository.findMovieNameByActorName(anyString())).thenReturn(movieList);

        boolean res = movieController.findSameAll("Elijah Wood","Orlando Bloom");

        Assertions.assertEquals(true,res);


    }
    @Test
    void findMoviesTest(){
        List<String> movieList = new ArrayList<>();
        movieList.add("God Father");
        movieList.add("The Lord Of The Rings");
        movieList.add("The Silence of the lamp");
        NameBasic nameBasic = new NameBasic();

        nameBasic.setPrimaryname("Al Pacino");

        Mockito.when(movieRepository.findMovieNameByActorName(anyString())).thenReturn(movieList);
        Mockito.when(nameRepository.findByPrimaryname(anyString())).thenReturn(nameBasic);


        List<String> movies = movieController.findAll("Al Pacino");


        Assertions.assertEquals(movies,movieList);
    }

}
