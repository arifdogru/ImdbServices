package com.arifdogru.imdb.repository;

import com.arifdogru.imdb.pojo.NameBasic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INameRepository extends JpaRepository<NameBasic,Long> {
    NameBasic findByPrimaryname(String name);
}
