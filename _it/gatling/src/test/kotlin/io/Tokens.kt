package io

import io.gatling.javaapi.core.Session
import java.time.Instant
import java.util.*

data class IdentityModel(
  val id: UUID
)

fun createSessionRequestTemplate(): (Session) -> String {
  return { session ->
    val identity = session.get<IdentityModel>("identity")
    """{
      |  "identity": {
      |    "id": "${identity.id}"
      |  },
      |  "createRefreshToken": true
      |}""".trimMargin()
  }
}