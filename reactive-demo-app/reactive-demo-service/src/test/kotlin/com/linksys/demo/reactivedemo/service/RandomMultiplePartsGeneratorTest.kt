package com.linksys.demo.reactivedemo.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import reactor.test.StepVerifier
import java.util.regex.Pattern

internal class RandomMultiplePartsGeneratorTest {

  private val underTest = RandomMultiplePartsGenerator(listOf(RandomPoolConfiguration("ABCD", 1), RandomPoolConfiguration("1234", 4)))
  private val re = Pattern.compile("[A-D][1-4]{4}")
  private val logger = LoggerFactory.getLogger(RandomMultiplePartsGeneratorTest::class.java)

  @Test
  fun `provide random multiple parts token`() {
    StepVerifier.create(underTest.next())
      .assertNext {s ->
        logger.debug("Generated: $s")
        assertEquals(5, s.length)
        assertTrue(re.matcher(s).matches())
      }
      .verifyComplete()
  }
}
