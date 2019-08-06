package com.tosok.catmash.cat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cats", path = "cats")
public interface CatRepository extends CrudRepository<Cat, String> {
}