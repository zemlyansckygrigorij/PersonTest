package com.example.persontest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonTestApplication
 */
@SpringBootApplication
class PersonTestApplication
fun main(args: Array<String>) {
    runApplication<PersonTestApplication>(*args)
}
