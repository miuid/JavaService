package com.hackerrank.sample;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.hackerrank.sample.entity.RecordEntity;
import com.hackerrank.sample.repository.RecordEntityRepository;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class SampleApplicationTests {
    @Autowired
    private MockMvc mockMvc;

//    @MockBean
//    private RecordEntityRepository recordEntityRepository;

    @Before
    public void setup() {
//        final RecordEntity entity = new RecordEntity();
//        entity.setId(123L);
//        entity.setName("test");
//        entity.setMessage("test message");
//        final List<RecordEntity> result = new ArrayList<>();
//        result.add(entity);
//
//        Mockito.doReturn(result).when(recordEntityRepository).findAll();
//        Mockito.when(recordEntityRepository.findAll()).thenReturn(result);
    }

    @Test
    public void selectTest() throws Exception {
        final String result = "[{\"id\":1,\"name\":\"Danni\",\"message\":\"Hello\"},{\"id\":2,\"name\":\"Mia\",\"message\":\"Good Kid\"}]";

        mockMvc.perform(get("/endpoint/select"))
                .andDo(print())
                .andExpect(content().string(result))
                .andExpect(
                        status().isOk());
    }

    @Test
    public void selectByIdTest() throws Exception {
        final String result = "{\"id\":1,\"name\":\"Danni\",\"message\":\"Hello\"}";
        mockMvc.perform(get("/endpoint/select/1"))
                .andDo(print())
                .andExpect(content().string(result))
                .andExpect(
                        status().isOk());
    }

    @Test
    public void insertTest() throws Exception {
        final String result = "{\"id\":3,\"name\":\"hello\",\"message\":\"world\"}";
        final JSONObject json = new JSONObject();
        json.put("name", "hello");
        json.put("message", "world");
        mockMvc.perform(post("/endpoint/insert")
                .contentType(MediaType.APPLICATION_JSON).content(json.toJSONString()))
                .andDo(print())
                .andExpect(content().string(result));
//                .andExpect(jsonPath("$.echo").value("hello world"))
//                .andExpect(
//                        status().isCreated());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete("/endpoint/delete/1"))
                .andDo(print())
                .andExpect(
                        status().isOk());
    }

    @Test
    public void insertWithoutBodyTest() throws Exception {
        mockMvc.perform(post("/endpoint/insert"))
                .andDo(print())
                .andExpect(
                        status().isBadRequest());
    }
}
