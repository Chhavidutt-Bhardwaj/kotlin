package com.kotlin.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class KotlinApplication

fun main(args: Array<String>) {
	runApplication<KotlinApplication>(*args)
	println("Hello World !!!")
	var name: String
	name= "Chhavidutt Sharma"
	println(name)
}
