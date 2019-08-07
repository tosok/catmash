package com.tosok.catmash.cat;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/cats")
public class CatController {
    private final CatRepository catRepository;

    @GetMapping
    public Iterable<Cat> getAllCats() {
        return catRepository.findAllByOrderByRankDesc();
    }

    @GetMapping("/random")
    public Iterable<Cat> getTwoRandomCats() {
        return catRepository.findTwoRandomCats();
    }

    @PostMapping("{id}/vote")
    public void vote(@PathVariable("id") String id) {
        Optional<Cat> cat = catRepository.findById(id);
        if (cat.isPresent()) {
            Cat updatedCat = cat.get();
            updatedCat.setRank(updatedCat.getRank() + 1);
            catRepository.save(updatedCat);
        }
    }
}
