package simulation.token

import io.createSessionRequestTemplate
import io.gatling.javaapi.core.CoreDsl.*
import io.gatling.javaapi.http.HttpDsl.http
import io.gatling.javaapi.http.HttpDsl.status
import main.configured
import main.identityRandomFeeder
import main.users
import org.slf4j.LoggerFactory

class BasicSimulation : BaseSimulation() {

  private val logger = LoggerFactory.getLogger(BasicSimulation::class.java)

  val createToken = http("SLOW")
    .post("/sessions")
    .body(StringBody(createSessionRequestTemplate()))

  val getToken = http("FAST")
    .get("/sessions/#{token}")

  //  val feeder = identityPooledFeeders(10)
  val feeder = identityRandomFeeder()

  val scn = scenario("Session")
    .feed(feeder)
    .configured(
      exec(
        createToken.check(
          status().shouldBe(200),
          jmesPath("sessionToken.token").saveAs("token")
        )
      )
//        .repeat(3)
//        .on(exec(
//          getToken.check(
//            status().shouldBe(200)
//          )
//        ))
    )
//      exec(
//        createToken.check(
//          status().shouldBe(200),
//          jmesPath("sessionToken.token").saveAs("latestToken"))
//      ).exec { session: Session ->
//        val token = session.get<String>("latestToken")
//        logger.debug("latest token: {}", token)
//        TokenFeeder.append(token)
//        val randomToken = TokenFeeder.random()
//        logger.debug("randomToken: {}", randomToken)
//        session.set("token", randomToken)
//      }.repeat(3)
//        .on(exec(
//          getToken.check(
//            status().shouldBe(200))
//        ))
//    )

  init {
    setUp(
      scn.users()
    ).protocols(httpProtocol)
  }

}