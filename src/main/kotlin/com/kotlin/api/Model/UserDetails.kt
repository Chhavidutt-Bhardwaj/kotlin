package com.kotlin.api.Model

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "user_details")
data class UserDetails (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long?,
    @Column(name = "user_name")
    var userName: String,
    @Column(name = "mobile_no")
    var mobileNo: Long

)