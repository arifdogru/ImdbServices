package com.arifdogru.imdb.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;
import javax.persistence.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
public class Principal {
    @Id
    @GeneratedValue
    private Integer ordering;
    private String tconst;
    private String nconst;
    private String category;
    private String job;
    private String characters;

}
