package com.example.persontest.entity

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import javax.persistence.*

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class PersonDao
 */
@Entity
@Table(name = "person")
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)
data class PersonDao (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,

    @Type(type = "jsonb")
    @Column(columnDefinition = "data")
    var data: Map<String, Any>?
) {
    constructor(): this(null, null)
}
