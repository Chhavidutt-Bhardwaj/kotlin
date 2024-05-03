package com.kotlin.api

import org.springframework.http.HttpStatus
import java.util.Objects

data class CommonResponse (
    val message : String,
    val objects: Any
)