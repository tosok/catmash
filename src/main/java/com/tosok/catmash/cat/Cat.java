package com.tosok.catmash.cat;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@Data
public class Cat {
    @Id
    private String id;
    private String url;
    private Integer rank;
}
