package com.kotlin.api.Business

import com.kotlin.api.CommonResponse
import com.kotlin.api.DAO.DoctorDAO
import com.kotlin.api.Model.DoctorPersonalDO
import com.kotlin.api.POJO.DoctorDetailsPO
import com.kotlin.api.Utility.CommonUtility
import com.kotlin.api.Utility.ConstantMsg
import com.kotlin.api.enum.DoctorType
import com.kotlin.api.enum.FormStatus
import com.kotlin.api.objectFactory.ObjectFactory
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.Date

@Component
class DoctorBusiness(private val doctorDAO: DoctorDAO) {

    private final val logger = LoggerFactory.getLogger(DoctorBusiness::class.java)

    fun saveOrUpdateDoctorPersonalDetails(doctorDetailsPO: DoctorDetailsPO): CommonResponse {
        try {
            val doctorPersonalDO : DoctorPersonalDO? = doctorDAO.getDoctorPersonalByDoctorId(doctorDetailsPO.doctorId)
            if(doctorPersonalDO == null){
                val doctorExist = doctorDAO.findByEmailId(doctorDetailsPO.emailId)
                if(doctorExist == null) {
                    val lastDoctorCode : String = doctorDAO.getLastDoctorCode() ?: "CP"
                    val doctorDetail = ObjectFactory.getDoctorPersonalDOFromPO(doctorDetailsPO, lastDoctorCode)
                    doctorDetail.formStatus = FormStatus.PERSONAL_DETAIL
                    doctorDetail.joiningDate = Date()
                    doctorDetail.updatedIn = FormStatus.PERSONAL_DETAIL.toString()
                    doctorDetail.updatedOn = Date()
                    doctorDetail.doctorStatus = DoctorType.IN_PROCESS
                    doctorDAO.save(doctorDetail)
                    val doctorDataPO : DoctorDetailsPO? = ObjectFactory.getDoctorPersonalPOFromDO(doctorDetail)
                    if(doctorDataPO != null) return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, doctorDataPO)
                } else {
                    return CommonUtility.getCommonResponse(ConstantMsg.ALREADY_EXIST, "User Exists")
                }
            } else {
                val doctorDetails : DoctorPersonalDO? =  ObjectFactory.getDoctorPersonalDOFromPOUpdate(doctorDetailsPO, doctorPersonalDO)
                if(doctorDetails != null){
                    doctorDetails.updatedIn = FormStatus.PERSONAL_DETAIL.toString()
                    doctorDetails.updatedOn = Date()
                    doctorDAO.save(doctorDetails)
                    val doctorDataPO : DoctorDetailsPO? = ObjectFactory.getDoctorPersonalPOFromDO(doctorDetails)
                    if(doctorDataPO != null) {
                        return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, doctorDataPO)
                    }
                }
            }
        } catch (e : Exception ){
            logger.error("An error occurred : "+ e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SOMETHING_WENT_WRONG, "")
    }

    fun getDoctorPersonalDetail(doctorId: String, doctorCode: String): CommonResponse {
        try {
            if(doctorCode == ""){
                val doctorPersonalDO = doctorDAO.getDoctorPersonalByDoctorCode(doctorCode)
                if(doctorPersonalDO != null){
                    val doctorDataPO : DoctorDetailsPO? = ObjectFactory.getDoctorPersonalPOFromDO(doctorPersonalDO)
                    if(doctorDataPO != null) {
                        return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, doctorDataPO)
                    }
                } else {
                    return CommonUtility.getCommonResponse(ConstantMsg.NOT_FOUND, "")
                }
            } else if(doctorId == ""){
                val doctorPersonalDO = doctorDAO.getDoctorPersonalByDoctorId(doctorId)
                if(doctorPersonalDO != null) {
                    val doctorDataPO : DoctorDetailsPO? = ObjectFactory.getDoctorPersonalPOFromDO(doctorPersonalDO)
                    if(doctorDataPO != null) {
                        return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, doctorDataPO)
                    }
                } else {
                    return CommonUtility.getCommonResponse(ConstantMsg.NOT_FOUND, "")
                }
            }
        } catch (e : Exception){
            logger.error("An error occurred : "+ e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SOMETHING_WENT_WRONG, "")
    }
}