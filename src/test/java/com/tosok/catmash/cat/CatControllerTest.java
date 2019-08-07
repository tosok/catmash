package com.tosok.catmash.cat;

import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

public class CatControllerTest {

    private MockMvc mvc;

    private CatRepository catRepository;

    @Before
    public void setup() {
        catRepository = Mockito.mock(CatRepository.class);
        mvc = MockMvcBuilders.standaloneSetup(new CatController(catRepository)).build();
    }

    @Test
    public void testGetAllCats_nominalCase() throws Exception {
        Mockito.when(catRepository.findAllByOrderByRankDesc())
                .thenReturn(Arrays.asList(
                        Cat.builder()
                            .id(RandomString.make(10))
                            .rank(100)
                            .url("http://random-image.to")
                            .build(),
                        Cat.builder()
                            .id(RandomString.make(10))
                            .rank(23)
                            .url("http://random-image.to")
                            .build(),
                        Cat.builder()
                            .id(RandomString.make(10))
                            .rank(1)
                            .url("http://random-image.to")
                            .build()));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/cats")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetTwoRandomCats_nominalCase() throws Exception {
        Mockito.when(catRepository.findTwoRandomCats())
                .thenReturn(Arrays.asList(
                        Cat.builder()
                                .id(RandomString.make(10))
                                .rank(100)
                                .url("http://random-image.to")
                                .build(),
                        Cat.builder()
                                .id(RandomString.make(10))
                                .rank(1)
                                .url("http://random-image.to")
                                .build()));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/cats/random")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testVote_nominalCase() throws Exception {
        String id = "12345";
        Mockito.when(catRepository.findById(id))
                .thenReturn(Optional.of(
                        Cat.builder()
                                .id(id)
                                .rank(100)
                                .url("http://random-image.to")
                                .build()));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/cats/"+id+"/vote")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        Mockito.verify(catRepository, times(1)).save(any());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        Assert.assertNotNull(mvcResult.getResponse().getContentAsString());
    }

}
