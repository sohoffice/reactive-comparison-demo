package com.linksys.demo.imperativedemo.service

import java.security.SecureRandom

interface TokenGenerator {
  fun next(): String
}

data class RandomPoolConfiguration(val pool: String, val length: Int)

object TokenCharacters {
  const val EXTENDED_READABLE = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ`~!@#$%^&*()_-+=[{]}\\|;:',\"<.>/? "

  const val READABLE = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_-"

  const val ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

  const val URL_FRIENDLY = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-._~()'!*:@,;"
}

class RandomTokenGenerator(private val conf: RandomPoolConfiguration) : TokenGenerator {
  private val random = SecureRandom()

  override fun next(): String {
    return (1..conf.length).asSequence()
      .map {
        val n = random.nextInt(conf.pool.length)
        conf.pool[n]
      }
      .joinToString(separator = "")
  }
}

class RandomMultiplePartsGenerator(private val confs: List<RandomPoolConfiguration>) : TokenGenerator {
  private val random = SecureRandom()

  override fun next(): String {
    return confs.flatMap { conf ->
      (1..conf.length).map {
        val n = random.nextInt(conf.pool.length)
        conf.pool[n]
      }
    }.joinToString(separator = "")
  }
}
