package com.tosok.catmash.cat;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cat {
    @Id
    private String id;
    private String url;
    private Integer rank;
}
