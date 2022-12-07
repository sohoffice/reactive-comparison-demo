package io

import io.gatling.javaapi.core.Session
import java.util.*

data class IdentityModel(
  val id: UUID
)

fun createSessionRequestTemplate(): (Session) -> String {
  return { session ->
    val identityId = session.get<UUID>("identityId")
    """{
      |  "identity": {
      |    "id": "${identityId}"
      |  },
      |  "createRefreshToken": true
      |}""".trimMargin()
  }
}