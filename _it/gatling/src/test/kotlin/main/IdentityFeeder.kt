package main

import io.IdentityModel
import java.security.SecureRandom
import java.util.UUID

fun identityFeeders(size: Int): Iterator<Map<String, IdentityModel>> {
  val pool = (0..size).map {
    val id = UUID.randomUUID()
    mapOf("identity" to IdentityModel(id))
  }.toList()
  val random = SecureRandom()

  return generateSequence {
    pool[random.nextInt(size)]
  }.iterator()
}
