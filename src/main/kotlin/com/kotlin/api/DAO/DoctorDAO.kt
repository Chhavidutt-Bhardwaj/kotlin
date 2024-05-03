package com.kotlin.api.DAO

import com.kotlin.api.Model.DoctorPersonalDO
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface DoctorDAO : CrudRepository<DoctorPersonalDO, Int> {
    @Query("select dpd from DoctorPersonalDO dpd where dpd.emailId = ?1")
    fun findByEmailId(emailId: String): DoctorPersonalDO?

    @Query("select doctorCode from DoctorPersonalDO dpd ORDER BY id DESC LIMIT 1")
    fun getLastDoctorCode(): String?

    @Query("select dpd from DoctorPersonalDO dpd where dpd.doctorId = ?1")
    fun getDoctorPersonalByDoctorId(doctorId: String): DoctorPersonalDO?

    @Query("select dpd from DoctorPersonalDO dpd where dpd.doctorCode = ?1")
    fun getDoctorPersonalByDoctorCode(doctorCode: String): DoctorPersonalDO?

    @Query("select dpd from DoctorPersonalDO dpd where dpd.phoneNumber = ?1")
    fun findByMobileNo(phoneNo: String): DoctorPersonalDO?
}