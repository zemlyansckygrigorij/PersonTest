package com.example.persontest.repo


import com.example.persontest.entity.PersonDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * interface PersonDaoRepository
 */
@Repository
interface PersonDaoRepository : CrudRepository<PersonDao, Long> {}