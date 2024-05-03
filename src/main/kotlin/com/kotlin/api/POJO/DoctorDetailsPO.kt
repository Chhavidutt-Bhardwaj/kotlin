package com.kotlin.api.POJO

import com.kotlin.api.enum.DoctorType
import com.kotlin.api.enum.FormStatus
import java.util.*

data class DoctorDetailsPO (
    var doctorId : String = "",
    var doctorName : String = "",
    var doctorCode : String = "",
    var emailId  : String = "",
    var dob : String = "",
    var phoneNumber : String = "",
    var panNo : String = "",
    var formStatus : FormStatus? = null,
    var qrCodeUrl : String = "",
    var verified : Boolean = false,
    var important : Boolean = false,
    var joiningDate : Date? = null,
    var scoutCode : String = "",
    var qrInstallationStatus : Boolean = false,
    var doctorStatus: DoctorType? = null,
    var updatedIn : String = "",
    var updatedOn : Date? = null
)