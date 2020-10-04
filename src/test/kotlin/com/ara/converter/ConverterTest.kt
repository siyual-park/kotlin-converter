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
    fun test() {
        val converter = Converter().apply {
            register(CreateUserRequestToUserMapper, MapperInfo(CreateUserRequest::class, Any::class))
        }

        val username = "test"
        val password = "testPassword"
        val user: User = converter.convert(CreateUserRequest(username, password), MapperInfo(CreateUserRequest::class, Any::class))

        assertEquals(user.username, username)
        assertEquals(user.password, password)
    }
}