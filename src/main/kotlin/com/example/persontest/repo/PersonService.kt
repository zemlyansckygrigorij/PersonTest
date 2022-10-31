package com.example.persontest.repo

import com.example.persontest.entity.Person

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface PersonService
 */
interface PersonService {
    fun save(person : Person)
    fun findById(id :Long) : Person
    fun findAll() : ArrayList<Person>
    fun deleteById(id :Long)
}