package com.ara.converter.mock

import java.time.LocalDate

data class User(
    val username: String,
    val password: String,
    val id: String? = null
) {
    val createdAt: LocalDate = LocalDate.now()
}