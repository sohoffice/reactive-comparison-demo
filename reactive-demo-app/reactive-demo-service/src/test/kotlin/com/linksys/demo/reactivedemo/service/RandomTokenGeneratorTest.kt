@file:OptIn(ExperimentalCoroutinesApi::class)

package com.linksys.demo.reactivedemo.service

import com.linksys.demo.reactivedemo.service.TokenCharacters.READABLE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import reactor.test.StepVerifier
import java.util.regex.Pattern

internal class RandomTokenGeneratorTest {

  private val pattern = Pattern.compile("[abcd]{5}")

  @Test
  fun `provide random token`() {
    val underTest1 = RandomTokenGenerator(RandomPoolConfiguration("abcd", 5))
    StepVerifier
      .create(underTest1.next())
      .assertNext { s1 ->
        logger.debug(s1)
        assertTrue(pattern.matcher(s1).matches())
      }
      .verifyComplete()

    val underTest2 = RandomTokenGenerator(RandomPoolConfiguration(READABLE, 10))
    StepVerifier
      .create(underTest2.next())
      .assertNext { s2 ->
        logger.debug(s2)
        assertEquals(10, s2.length)
      }
      .verifyComplete()
  }

  private val logger = LoggerFactory.getLogger(RandomTokenGeneratorTest::class.java)
}
