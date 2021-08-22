package com.example.kingcafe.restdocs

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print

class RestDocsControllerTest : BaseControllerTest() {
    @Test
    fun `RestDocs Test`() {
        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andDo(
                document(
                    "test",
//                    requestFields(
//                        fieldWithPath("name").description("RestDocs Test"),
//                        fieldWithPath("description").description("RestDocs Test!! 무야호")
//                    ),
                    responseFields(
                        fieldWithPath("id").description("ID"),
                        fieldWithPath("name").description("이름"),
                        fieldWithPath("publisher").description("작성자")
                    )
                )
            )
    }
}