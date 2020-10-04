package com.ara.converter

import com.ara.converter.mock.CreateUserRequest
import com.ara.converter.mock.CreateUserRequestToUserMapper
import com.ara.converter.mock.User
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConverterTest {
    @Test
    fun testFromDSL() {
        val converter = Converter().apply {
            register(CreateUserRequestToUserMapper)
        }

        val username = "test"
        val password = "testPassword"
        val user: User = converter.convert(CreateUserRequest(username, password))

        assertEquals(user.username, username)
        assertEquals(user.password, password)
    }

    @Test
    fun testConvertSuperClass() {
        val converter = Converter().apply {
            register(CreateUserRequestToUserMapper, MapperInfo(CreateUserRequest::class, Any::class))
            register(CreateUserRequestToUserMapper)
        }

        val username = "test"
        val password = "testPassword"
        val userRequest = CreateUserRequest(username, password)

        val userFromAny: Any = converter.convert(userRequest)
        val userFromUser: User = converter.convert(userRequest)

        assertEquals(userFromAny, userFromUser)
    }
}