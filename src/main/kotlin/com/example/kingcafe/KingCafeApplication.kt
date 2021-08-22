package com.example.kingcafe

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KingCafeApplication

fun main(args: Array<String>) {
    runApplication<KingCafeApplication>(*args)
}

@RestController
class RestDocsController {
    @GetMapping("/")
    fun hello() : Test {
        return Test()
    }

    class Test(
        val id: Long = 1L,
        val name: String = "Rest Docs Test",
        val publisher: String = "Maeve"
    )
}
