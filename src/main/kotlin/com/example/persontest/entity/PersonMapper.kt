package com.example.persontest.entity

import com.example.persontest.service.DataFormatter

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonMapper
 */
class PersonMapper {
    var formatter = DataFormatter()

    fun getPersonFromDao(personDao: PersonDao): Person?{
        var person = Person()
        person.id = personDao.id
        var birthdayStr = ""
        var data = personDao.data
        if (data != null) {
            person.name = data.getValue("name") as String
            person.location = data.getValue("location") as String
            birthdayStr = data.getValue("birthday").toString() as String
            person.birthDay = formatter.stringToDate(birthdayStr)
        }else throw  Exception()

        return person
    }

    fun getPersonDao(person: Person): PersonDao?{
        val id = person.id
        var data: Map<String, Any>
        var dateString = formatter.dateToString(person.birthDay!!)
        data= mapOf("name" to person.name as Any,"birthday" to dateString as Any, "location" to person.location as Any)
        return  PersonDao(id ,data)
    }
    fun getPersonWeb(person: Person): PersonWeb?{
        var personWeb = PersonWeb();
        personWeb.id = person.id
        personWeb.name = person.name
        personWeb.location = person.location
        personWeb.birthDay = person.birthDay?.let { formatter.dateToString(it) }
        return  personWeb
    }
    fun getPersonWebList(persons: ArrayList<Person>): ArrayList<PersonWeb>{
        var personWebList = ArrayList<PersonWeb>()
        persons.forEach {
            personWebList.add(getPersonWeb(it)!!)
        }
        return personWebList;
    }
}