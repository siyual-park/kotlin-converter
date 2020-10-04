package com.ara.converter

import com.ara.converter.matcher.ExactMatcher
import com.ara.converter.matcher.ExtendMatcher
import com.ara.converter.matcher.NameMatcher
import com.ara.converter.mock.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConverterTest {
    @Test
    fun testExactMatch() {
        val converter = Converter.create(ExactMatcher.create()).apply {
            register(CreateUserRequestToUserMapper)
        }

        val username = "test"
        val password = "testPassword"
        val user: User = converter.convert(CreateUserRequest(username, password))

        assertEquals(user.username, username)
        assertEquals(user.password, password)
    }

    @Test
    fun testExtendMatch() {
        val converter = Converter.create(ExtendMatcher.create()).apply {
            register(CreateUserRequestToUserMapper)
        }

        val username = "test"
        val password = "testPassword"

        val exactMatched: User = converter.convert(CreateUserRequest(username, password))
        val extendMatched: Any = converter.convert(CreateUserRequest(username, password))

        assertEquals(exactMatched, extendMatched)
    }

    @Test
    fun testNamedMatch() {
        val converter = Converter.create(NameMatcher.from(ExtendMatcher.create()), ExtendMatcher.create()).apply {
            register(IntMinusMapper, "minus")
            register(IntPlusMapper, "plus")
            register(IntPassMapper)
        }

        val origin = 1

        val pass: Int = converter.convert(origin)
        assertEquals(pass, origin)

        val plus: Int = converter.convert(origin, "plus")
        assertEquals(plus, origin + 1)

        val minus: Int = converter.convert(origin, "minus")
        assertEquals(minus, origin - 1)
    }
}