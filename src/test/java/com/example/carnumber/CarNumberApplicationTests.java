package com.example.carnumber;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class CarNumberApplicationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    void crudTest() throws Exception {
        mockMvc.perform(get("/numbers/1"))
                .andExpect(
                        content()
                                .json("{\n" +
                                        "  \"id\": 1,\n" +
                                        "  \"firstLetter\": \"А\",\n" +
                                        "  \"number\": \"001\",\n" +
                                        "  \"secondLetter\": \"А\",\n" +
                                        "  \"thirdLetter\": \"А\"\n" +
                                        "}")
                );

        mockMvc.perform(get("/numbers/"))
                .andExpect(
                        content()
                                .json("[\n" +
                                        "  {\n" +
                                        "    \"id\": 1,\n" +
                                        "    \"firstLetter\": \"А\",\n" +
                                        "    \"number\": \"001\",\n" +
                                        "    \"secondLetter\": \"А\",\n" +
                                        "    \"thirdLetter\": \"А\"\n" +
                                        "  },\n" +
                                        "  {\n" +
                                        "    \"id\": 2,\n" +
                                        "    \"firstLetter\": \"А\",\n" +
                                        "    \"number\": \"002\",\n" +
                                        "    \"secondLetter\": \"А\",\n" +
                                        "    \"thirdLetter\": \"А\"\n" +
                                        "  },\n" +
                                        "  {\n" +
                                        "    \"id\": 3,\n" +
                                        "    \"firstLetter\": \"А\",\n" +
                                        "    \"number\": \"113\",\n" +
                                        "    \"secondLetter\": \"А\",\n" +
                                        "    \"thirdLetter\": \"А\"\n" +
                                        "  }\n" +
                                        "]")
                );

        mockMvc.perform(get("/numbers/generateNext/"))
                .andExpect(
                        content()
                                .string("А114АА 116 RUS")
                );

        mockMvc.perform(get("/numbers/generateNext/"))
                .andExpect(
                        content()
                                .string("А115АА 116 RUS")
                );

    }
}

