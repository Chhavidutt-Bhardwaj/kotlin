package com.kotlin.api.objectFactory

import com.kotlin.api.Model.DoctorPersonalDO
import com.kotlin.api.POJO.DoctorDetailsPO
import com.kotlin.api.Utility.CommonUtility
import com.kotlin.api.Utility.TimeUtility

class ObjectFactory {
    companion object {
        fun getDoctorPersonalDOFromPO(doctorDetailsPO: DoctorDetailsPO, doctorCode : String) : DoctorPersonalDO {
            var x : DoctorPersonalDO = DoctorPersonalDO()
            try {
                x.doctorId = CommonUtility.randomAlphaNumeric(32)
                x.doctorName = doctorDetailsPO.doctorName
                x.emailId = doctorDetailsPO.emailId
                x.phoneNumber = doctorDetailsPO.phoneNumber
                x.dob = TimeUtility.convertStrToDate(doctorDetailsPO.dob)
                x.important = doctorDetailsPO.important
                x.scoutCode = doctorDetailsPO.scoutCode
                x.panNo = doctorDetailsPO.panNo
                x.doctorCode = CommonUtility.getDoctorCode(doctorCode).toString()

                return x
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return x;
        }

        fun getDoctorPersonalDOFromPOUpdate(doctorDetailsPO: DoctorDetailsPO, doctorPersonalDO: DoctorPersonalDO): DoctorPersonalDO? {
            try {
                if(doctorDetailsPO.doctorId.isNotEmpty()) doctorPersonalDO.doctorId = doctorDetailsPO.doctorId
                if(doctorDetailsPO.doctorStatus.toString().isNotEmpty()) doctorPersonalDO.formStatus = doctorDetailsPO.formStatus
                if(doctorDetailsPO.important) doctorPersonalDO.important = true
                if(doctorDetailsPO.doctorCode.isNotEmpty()) doctorPersonalDO.doctorCode = doctorDetailsPO.doctorCode
                if(doctorDetailsPO.verified) doctorPersonalDO.verified = true
                if(doctorDetailsPO.qrInstallationStatus) doctorPersonalDO.qrInstallationStatus = true
                if(doctorDetailsPO.dob.isNotEmpty()) doctorPersonalDO.dob = TimeUtility.convertStrToDate(doctorDetailsPO.dob)
                if(doctorDetailsPO.doctorName.isNotEmpty()) doctorPersonalDO.doctorName = doctorDetailsPO.doctorName
                if(doctorDetailsPO.emailId.isNotEmpty()) doctorPersonalDO.emailId = doctorDetailsPO.emailId
                if(doctorDetailsPO.phoneNumber.isNotEmpty()) doctorPersonalDO.phoneNumber = doctorDetailsPO.phoneNumber
                if(doctorDetailsPO.formStatus.toString().isNotEmpty()) doctorPersonalDO.formStatus = doctorDetailsPO.formStatus
                if(doctorDetailsPO.joiningDate.toString().isNotEmpty()) doctorPersonalDO.joiningDate = doctorDetailsPO.joiningDate
                if(doctorDetailsPO.panNo.isNotEmpty()) doctorPersonalDO.panNo = doctorDetailsPO.panNo
                if(doctorDetailsPO.scoutCode.isNotEmpty()) doctorPersonalDO.scoutCode = doctorDetailsPO.scoutCode
                if(doctorDetailsPO.qrCodeUrl.isNotEmpty()) doctorPersonalDO.qrCodeUrl = doctorDetailsPO.qrCodeUrl
                return doctorPersonalDO
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null;
        }

        fun getDoctorPersonalPOFromDO(doctorDetailDO: DoctorPersonalDO): DoctorDetailsPO? {
            try{
                val x = DoctorDetailsPO()
                x.doctorId = doctorDetailDO.doctorId
                x.doctorName = doctorDetailDO.doctorName
                x.emailId = doctorDetailDO.emailId
                x.phoneNumber = doctorDetailDO.phoneNumber
                if(doctorDetailDO.dob != null) {
                    x.dob = TimeUtility.getDateToStrYMDWithoutTimeStamp(doctorDetailDO.dob!!)
                }
                x.important = doctorDetailDO.important
                x.scoutCode = doctorDetailDO.scoutCode
                x.panNo = doctorDetailDO.panNo
                x.doctorCode = doctorDetailDO.doctorCode
                x.qrCodeUrl = doctorDetailDO.qrCodeUrl
                x.qrInstallationStatus = doctorDetailDO.qrInstallationStatus
                x.joiningDate = doctorDetailDO.joiningDate
                x.updatedIn = doctorDetailDO.updatedIn
                x.updatedOn = doctorDetailDO.updatedOn
                x.formStatus = doctorDetailDO.formStatus
                x.verified = doctorDetailDO.verified
                x.doctorStatus = doctorDetailDO.doctorStatus
                return x
            } catch (e : Exception){
                e.printStackTrace()
            }
            return null;
        }

    }
}