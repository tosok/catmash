package com.tosok.catmash.cat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends CrudRepository<Cat, String> {
    @Query(nativeQuery = true, value="select * from cat order by random() limit 2")
    List<Cat> findTwoRandomCats();

    List<Cat> findAllByOrderByRankDesc();
}