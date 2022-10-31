package com.example.persontest.repo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class RepositoriesTests
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTests @Autowired constructor(
    val personDaoRepository: PersonDaoRepository) {

    @Test
    fun `When findByIdOrNull then return Article`() {
        val persons = personDaoRepository.findAll();
        assertThat(personDaoRepository)
        assertThat(persons.toList().size).isEqualTo(8)
        val personOpt = personDaoRepository.findById(1)
        assertThat(personOpt)
        val person = personOpt.get()
        assertThat(person.id).isEqualTo(1)
        assertThat(person.data?.get("name")).isEqualTo("Ершо́в Пётр Па́влович")
        assertThat(person.data?.get("birthday")).isEqualTo("22/02/1815")
        assertThat(person.data?.get("location")).isEqualTo("Тобольск")
        person.data?.let { assertThat(it.size).isEqualTo(3) }
    }
}