package com.example.data.storage.roomstorage

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

class Example {
    suspend fun main(){

        val peopleFlow = listOf(
            Person("Tom", 37),
            Person("Bill", 5),
            Person("Sam", 14),
            Person("Bob", 21),
        ).asFlow()

        peopleFlow.map{ person -> object{
            val name = person.name
            val isAdult = person.age > 17
        }}.collect { user -> println("name: ${user.name}   adult:  ${user.isAdult} ")}
    }

    data class Person(val name: String, val age: Int)
}