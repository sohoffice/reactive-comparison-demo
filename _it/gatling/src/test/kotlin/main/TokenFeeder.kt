package main

import java.security.SecureRandom
import java.util.concurrent.CopyOnWriteArrayList

object TokenFeeder {
  private const val CAPACITY = 1000
  private val tokens = CopyOnWriteArrayList<String>()
  private val random = SecureRandom()

  fun append(token: String) {
    if (tokens.size >= CAPACITY) {
      tokens.removeFirst()
    }
    tokens.add(token)
  }

  fun random(): String {
    return if (tokens.isNotEmpty()) {
      tokens[random.nextInt(if (tokens.size > CAPACITY) CAPACITY else tokens.size)]
    } else {
      ""
    }
  }
}