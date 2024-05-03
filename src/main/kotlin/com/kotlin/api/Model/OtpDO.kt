package com.kotlin.api.Model

import jakarta.persistence.*
import java.util.Date
@Entity
@Table(name = "otp_detail")
data class OtpDO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @Column(name = "mobile_number")
    var mobileNumber : Long = 0,
    @Column(name = "otp")
    var otp : Int = 0,
    @Column(name = "added_on")
    var addedOn : Date? = null
)
