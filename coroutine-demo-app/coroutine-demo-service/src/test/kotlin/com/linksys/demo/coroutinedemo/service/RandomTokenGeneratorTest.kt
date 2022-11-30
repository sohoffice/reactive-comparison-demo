@file:OptIn(ExperimentalCoroutinesApi::class)

package com.linksys.demo.coroutinedemo.service

import com.linksys.demo.coroutinedemo.service.TokenCharacters.READABLE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.util.regex.Pattern

internal class RandomTokenGeneratorTest {

  private val pattern = Pattern.compile("[abcd]{5}")

  @Test
  fun `provide random token`() = runTest {
    val underTest1 = RandomTokenGenerator(RandomPoolConfiguration("abcd", 5))
    val s1 = underTest1.next()
    logger.debug(s1)
    assertTrue(pattern.matcher(s1).matches())

    val underTest2 = RandomTokenGenerator(RandomPoolConfiguration(READABLE, 10))
    val s2 = underTest2.next()
    logger.debug(s2)
    assertEquals(10, s2.length)
  }

  private val logger = LoggerFactory.getLogger(RandomTokenGeneratorTest::class.java)
}
