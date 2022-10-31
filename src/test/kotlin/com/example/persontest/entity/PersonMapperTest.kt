package com.example.persontest.entity

import com.example.persontest.repo.PersonDaoRepository
import com.example.persontest.service.DataFormatter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.util.*


/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonMapperTest
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
internal class PersonMapperTest @Autowired constructor(
    val personDaoRepository: PersonDaoRepository
) {
    var personMapper = PersonMapper()
    var formatter = DataFormatter()

    @Test
    fun getPersonFromDao() {

        val personOpt = personDaoRepository.findById(1)
        assertThat(personOpt)
        var personDao = personOpt.get()
        var person :Person? = personMapper.getPersonFromDao(personDao)
        assertThat(person)
        if (person != null) {
            assertThat(person.name).isEqualTo("Ершо́в Пётр Па́влович")
            assertThat(person.location).isEqualTo("Тобольск")
            assertThat(person.birthDay?.let { formatter.dateToString(it) }).isEqualTo("22/02/1815")
        }
    }

    @Test
    fun getPersonDao() {
        var person = Person()
        person.name = "test test test "
        person.location = "testLocation"
        var birthDay = Date()
        person.birthDay = birthDay

        var personDao :PersonDao? = personMapper.getPersonDao(person)
        assertThat(personDao)
        assertThat(personDao?.id).isEqualTo(null)
        assertThat(personDao?.data)
        var data =personDao?.data
        assertThat(data?.get("name")).isEqualTo("test test test ")
        assertThat(data?.get("location")).isEqualTo("testLocation")
        assertThat(data?.get("birthday")).isEqualTo(formatter.dateToString(person.birthDay!!))
    }
}