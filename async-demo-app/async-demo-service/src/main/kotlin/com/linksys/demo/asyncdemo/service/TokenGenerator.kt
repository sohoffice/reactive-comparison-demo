package com.linksys.demo.asyncdemo.service

import java.security.SecureRandom
import java.util.concurrent.CompletableFuture

interface TokenGenerator {
  fun next(): CompletableFuture<String>
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

  override fun next(): CompletableFuture<String> {
    return CompletableFuture.supplyAsync {
      (1..conf.length).asSequence()
        .map {
          val n = random.nextInt(conf.pool.length)
          conf.pool[n]
        }
        .joinToString(separator = "")
    }
  }
}

class RandomMultiplePartsGenerator(private val confs: List<RandomPoolConfiguration>) : TokenGenerator {
  private val random = SecureRandom()

  override fun next(): CompletableFuture<String> {
    return CompletableFuture.supplyAsync {
      confs.flatMap { conf ->
        (1..conf.length).map {
          val n = random.nextInt(conf.pool.length)
          conf.pool[n]
        }
      }.joinToString(separator = "")
    }
  }
}
