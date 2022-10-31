package com.example.persontest.repo

import com.example.persontest.entity.Person
import com.example.persontest.service.DataFormatter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import javax.transaction.Transactional

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonServiceImplTest
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
internal class PersonServiceImplTest @Autowired constructor(
    val personServiceImpl: PersonServiceImpl)  {
    var formatter = DataFormatter()

    @Test
    fun save() {
        val size =  personServiceImpl.findAll().count();
        var person = Person()
        person.name = "test test test "
        person.location = "testLocation"
        var birthDay = Date()
        person.birthDay = birthDay
        personServiceImpl.save(person)
        val persons =  personServiceImpl.findAll()!!;
        assertThat(size+1).isEqualTo(persons.count())
        val id = persons.last().id
        val personById = personServiceImpl.findById(id!!)

        assertThat(personById.name).isEqualTo("test test test ")
        assertThat(personById.location).isEqualTo("testLocation")
        assertThat(personById.birthDay?.let { formatter.dateToString(it) }).isEqualTo(formatter.dateToString(birthDay))
    }

    @Test
    fun findById() {
        val person = personServiceImpl.findById(1)
        assertThat(person)
        assertThat(person.id).isEqualTo(1)
        assertThat(person.location).isEqualTo("Тобольск")
        assertThat(person.name).isEqualTo("Ершо́в Пётр Па́влович")
        assertThat(person.birthDay?.let { formatter.dateToString(it) }).isEqualTo("22/02/1815")
    }

    @Test
    fun findByIdOutOfListSize() {
        val size =  personServiceImpl.findAll().count();
        assertThrows(Exception::class.java) {
            personServiceImpl.findById(size+2L)
        }
    }

    @Test
    fun findAll() {
        val persons =  personServiceImpl.findAll();
        assertThat(persons.toList().size).isEqualTo(8)
    }

    @Test
    fun deleteById() {
        assertThat(personServiceImpl.findAll().toList().size).isEqualTo(8)
        personServiceImpl.deleteById(3)
        assertThat(personServiceImpl.findAll().toList().size).isEqualTo(7)
    }
}