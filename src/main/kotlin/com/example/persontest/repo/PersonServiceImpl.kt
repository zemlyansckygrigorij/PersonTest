package com.example.persontest.repo

import com.example.persontest.entity.Person
import com.example.persontest.entity.PersonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonServiceImpl
 */
@Service
class PersonServiceImpl @Autowired constructor(
    val personDaoRepository: PersonDaoRepository): PersonService{

    var personMapper = PersonMapper()

    override fun save(person: Person) {
       personDaoRepository.save(personMapper.getPersonDao(person)!!)
    }

    override fun findById(id: Long): Person {
        var personDaoOpt = personDaoRepository.findById(id)
        if(personDaoOpt.isPresent) {
            return personMapper
                .getPersonFromDao(personDaoOpt.get())!!
        }else{
            throw Exception("Exception")
        }
    }

    override fun findAll(): ArrayList<Person>{
        var personList = ArrayList<Person>();

        var  personDaoList = personDaoRepository.findAll().toList();
        personDaoList.forEach{
            personList.add(personMapper.getPersonFromDao(it)!!)}
        return personList
    }

    override fun deleteById(id: Long) {
        personDaoRepository.deleteById(id)
    }
}