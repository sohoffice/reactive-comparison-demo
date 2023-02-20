package com.linksys.demo.asyncdemo.config

import com.linksys.demo.asyncdemo.Constants.TASK_EXECUTOR_SERVICE
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class AsyncDemoConfig {

  @Bean(name = arrayOf(TASK_EXECUTOR_SERVICE))
  fun taskExecutorService(): Executor {
    val executor = ThreadPoolTaskExecutor()
    executor.corePoolSize = 1000
    executor.maxPoolSize = 10000
    executor.queueCapacity = 3000
    executor.setThreadNamePrefix("task-service-")
    return executor
  }
}