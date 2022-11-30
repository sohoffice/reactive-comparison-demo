package com.linksys.demo.reactivedemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories(basePackages = ["com.linksys.demo.reactivedemo.mongo.repository"])
class ReactiveDemoApplication

fun main(args: Array<String>) {
  runApplication<ReactiveDemoApplication>(*args)
}
