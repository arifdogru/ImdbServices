package com.arifdogru.imdb.pojo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;
import javax.persistence.*;
import java.util.Date;

@Entity

@Getter
@Setter
@NoArgsConstructor
@Table (appliesTo = "title_basic")
public class TitleBasic {

    @Id
    private String tconst;
    private String titletype;
    private String primarytitle;
    private String originaltitle;
    private Boolean isadult;
    private Date startyear;
    private Date endyear;
    private Long runtimeminutes;

}
