package com.example.kingcafe.restdocs

import org.springframework.boot.test.autoconfigure.restdocs.RestDocsMockMvcConfigurationCustomizer
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint

@TestConfiguration
class RestDocsConfiguration {

    @Bean
    fun restDocsMockMvcConfigurationCustomizer(): RestDocsMockMvcConfigurationCustomizer {
        return RestDocsMockMvcConfigurationCustomizer {
            it.operationPreprocessors()
                .withRequestDefaults(prettyPrint()) // 문서 조각 생성시 예쁘게 포매팅
                .withResponseDefaults(prettyPrint()) // 문서 조각 생성시 예쁘게 포매팅
        }
    }
}