package com.linksys.demo.asyncdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableMongoRepositories(basePackages = ["com.linksys.demo.asyncdemo.mongo.repository"])
@EnableAsync(proxyTargetClass = true)
class AsyncDemoApplication

fun main(args: Array<String>) {
  runApplication<AsyncDemoApplication>(*args)
}
