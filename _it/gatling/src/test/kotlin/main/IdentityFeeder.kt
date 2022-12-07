package main

import io.IdentityModel
import java.security.SecureRandom
import java.util.UUID

fun identityPooledFeeders(poolSize: Int): Iterator<Map<String, UUID>> {
  val pool = (0..poolSize).map {
    mapOf("identityId" to UUID.randomUUID())
  }.toList()
  val random = SecureRandom()

  return generateSequence {
    pool[random.nextInt(poolSize)]
  }.iterator()
}

fun identityRandomFeeder(): Iterator<Map<String, UUID>> {
  return generateSequence {
    mapOf("identityId" to UUID.randomUUID())
  }.iterator()
}
