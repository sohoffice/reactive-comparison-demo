package com.linksys.demo.coroutinedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories(basePackages = ["com.linksys.demo.coroutinedemo.mongo.repository"])
class CoroutineDemoApplication

fun main(args: Array<String>) {
  runApplication<CoroutineDemoApplication>(*args)
}
