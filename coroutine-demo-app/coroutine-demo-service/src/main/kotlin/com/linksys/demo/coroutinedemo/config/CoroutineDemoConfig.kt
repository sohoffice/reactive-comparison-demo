package com.linksys.demo.coroutinedemo.config

import linksys.core.reactive.web.RequestLoggingWebFilter
import org.springframework.context.annotation.Bean
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.web.reactive.config.WebFluxConfigurer

//@Configuration
class CoroutineDemoConfig {

  @Bean
  @Order(Ordered.HIGHEST_PRECEDENCE)
  fun requestLoggingWebFilter(): RequestLoggingWebFilter {
    val f = RequestLoggingWebFilter()
    f.logHeaders = true
    return f
  }
}

//@Configuration
class MyConfig : WebFluxConfigurer {

  override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
    configurer.defaultCodecs().enableLoggingRequestDetails(true)
  }
}