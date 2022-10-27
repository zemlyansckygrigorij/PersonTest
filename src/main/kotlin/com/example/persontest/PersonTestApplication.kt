package com.example.persontest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PersonTestApplication

fun main(args: Array<String>) {
    runApplication<PersonTestApplication>(*args)
}
