package simulation.token

import io.createSessionRequestTemplate
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.core.Session
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import main.TokenFeeder
import main.configured
import main.identityFeeders
import main.users
import org.slf4j.LoggerFactory

class BasicSimulation : BaseSimulation() {

  private val logger = LoggerFactory.getLogger(BasicSimulation::class.java)

  val createToken = http("POST /sessions")
    .post("/sessions")
    .body(StringBody(createSessionRequestTemplate()))

  val getToken = http("GET  /sessions/{token}")
    .get("/sessions/#{token}")

  val feeder = identityFeeders(10)
  val scn = scenario("Session")
    .feed(identityFeeders(10))
    .configured(
      exec(
        createToken.check(
          status().shouldBe(200),
          jmesPath("sessionToken.token").saveAs("latestToken"))
      ).exec { session: Session ->
        val token = session.get<String>("latestToken")
        logger.debug("latest token: {}", token)
        TokenFeeder.append(token)
        val randomToken = TokenFeeder.random()
        logger.debug("randomToken: {}", randomToken)
        session.set("token", randomToken)
      }.repeat(3)
        .on(exec(
          getToken.check(
            status().shouldBe(200))
        ))
    )

  init {
    setUp(
      scn.users()
    ).protocols(httpProtocol)
  }

}