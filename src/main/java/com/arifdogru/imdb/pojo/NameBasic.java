package com.arifdogru.imdb.pojo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Table;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(appliesTo = "name_basic")
public class NameBasic {

    @Id
    @GeneratedValue
    private String nConst;

    private String primaryname;

    private Date birthyear;
    private Date deathyear;

    private String primaryprofession;
    private String knownfortitles;

}
