package com.kotlin.api.Business

import com.kotlin.api.CommonResponse
import com.kotlin.api.DAO.MainDAO
import com.kotlin.api.DAO.OtpDAO
import com.kotlin.api.Model.OtpDO
import com.kotlin.api.Model.UserDetails
import com.kotlin.api.Utility.CommonUtility
import com.kotlin.api.Utility.ConstantMsg
import com.kotlin.api.auth.JWTUtility
import com.kotlin.api.auth.UserDetailsService
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Component
import java.util.*

@Component
class MainBusiness(private val mainDAO: MainDAO,
                   private val otpDAO: OtpDAO,
                   private val authenticationManager: AuthenticationManager,
                   private val jwtUtility: JWTUtility,
                   private val userDetailsService : UserDetailsService) {

    private val logger = LoggerFactory.getLogger(MainBusiness::class.java)

    fun addUserDetails(userDetails: UserDetails): CommonResponse {
        try {
            if(userDetails.id == null){
                mainDAO.save(userDetails)
                return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, userDetails)
            }
        } catch (e:Exception){
            logger.error("An error occured ", e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, userDetails)
    }

    fun getUserDetails(mobileNumber: Long): CommonResponse {
        var userDetails: UserDetails?
        try{
            userDetails = mainDAO.getUserDetails(mobileNumber)
            if(userDetails != null) {
                return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, userDetails)
            }
            return CommonUtility.getCommonResponse(ConstantMsg.NOT_FOUND, "")
        } catch ( e : Exception){
            logger.error("An error occured ", e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SOMETHING_WENT_WRONG, "")
    }

    fun sendOtp(mobileNumber: Long): CommonResponse {
        try {
            if (mobileNumber.toString().length == 10) {
                val otp: Int = CommonUtility.generate4DigitOTP()
                val otpDO = OtpDO()
                otpDO.otp = otp
                otpDO.mobileNumber = mobileNumber
                otpDO.addedOn = Date()
                val otpMessage =
                    "Dear Customer , Your OTP is $otp. Use this otp to log in to Kotlin Project"
                val toPhoneNumber = PhoneNumber("+${mobileNumber}")
                val fromPhoneNumber = PhoneNumber("+919812894230")

                val message: Message = Message.creator(toPhoneNumber, fromPhoneNumber, otpMessage).create()
                return CommonUtility.getCommonResponse(ConstantMsg.SUCCESS, message)
            } else {
                return CommonUtility.getCommonResponse(ConstantMsg.FAILURE, "Please Enter Correct order of Mobile No")
            }
        } catch (e: Exception){
            logger.error("An error occured ", e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SOMETHING_WENT_WRONG, "")
    }

    fun verifyOtp(otp: Int, mobileNumber: Long): CommonResponse {
        try{
            if(mobileNumber.toString().length == 10){
               val otpDO : OtpDO? = otpDAO.findbyMobileNo(mobileNumber)
                if(otpDO != null){
                    if(otpDO.otp == otp){
                        val status : String = createAuthenticationToken(mobileNumber)
                        otpDAO.deleteById(otpDO.id)
                    }
                }
            } else {
                return CommonUtility.getCommonResponse(ConstantMsg.FAILURE, "Please Enter Correct order of Mobile No")
            }
        } catch (e : Exception){
            logger.error("An error occured ", e.message, e)
            e.printStackTrace()
        }
        return CommonUtility.getCommonResponse(ConstantMsg.SOMETHING_WENT_WRONG, "")
    }

    private fun createAuthenticationToken(mobileNumber: Long) : String{
        try{
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(mobileNumber, ""))
        } catch (e: Exception){
            throw java.lang.Exception("Incorrect username or password", e)
        }
        val userDetails: org.springframework.security.core.userdetails.UserDetails? = userDetailsService
            .loadUserByUsername(mobileNumber.toString())
        return jwtUtility.generateToken(userDetails)
    }

}