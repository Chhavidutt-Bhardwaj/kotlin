package com.kotlin.api.Service

import com.kotlin.api.Business.DoctorBusiness
import com.kotlin.api.CommonResponse
import com.kotlin.api.POJO.DoctorDetailsPO
import org.springframework.stereotype.Service

@Service
class DoctorService(private val doctorBusiness: DoctorBusiness) {
    fun saveOrUpdateDoctorPersonalDetails(doctorDetailsPO: DoctorDetailsPO): CommonResponse {
        return doctorBusiness.saveOrUpdateDoctorPersonalDetails(doctorDetailsPO)
    }

    fun getDoctorPersonalDetail(doctorId: String, doctorCode: String): CommonResponse {
        return doctorBusiness.getDoctorPersonalDetail(doctorId, doctorCode)
    }

}
