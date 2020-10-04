package com.ara.converter.mock

import com.ara.converter.mapper.Mapper

object CreateUserRequestToUserMapper : Mapper<CreateUserRequest, User> {
    override fun map(source: CreateUserRequest): User {
        return User(source.username, source.password)
    }
}