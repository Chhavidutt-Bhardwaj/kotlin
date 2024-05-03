package com.kotlin.api.DAO

import com.kotlin.api.Model.UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface MainDAO : JpaRepository<UserDetails, Long>{
    @Query("Select ud from UserDetails ud where ud.mobileNo = ?1")
    fun getUserDetails(mobileNumber: Long) : UserDetails?
}