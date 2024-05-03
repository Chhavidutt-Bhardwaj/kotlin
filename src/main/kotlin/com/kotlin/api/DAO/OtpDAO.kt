package com.kotlin.api.DAO

import com.kotlin.api.Model.OtpDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpDAO : CrudRepository<OtpDO, Int>{
    @Query("select od from OtpDo od where od.mobileNumber = ?1")
    fun findbyMobileNo(mobileNumber: Long): OtpDO?
}