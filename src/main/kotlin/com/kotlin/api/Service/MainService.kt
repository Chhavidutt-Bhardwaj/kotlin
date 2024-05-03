package com.kotlin.api.Service

import com.kotlin.api.Business.MainBusiness
import com.kotlin.api.CommonResponse
import com.kotlin.api.Model.UserDetails
import com.kotlin.api.Utility.ConstantMsg
import org.springframework.stereotype.Service

@Service
class MainService(private val mainBusiness: MainBusiness) {
    fun addUserDetail(userDetails: UserDetails) : CommonResponse{
        return mainBusiness.addUserDetails(userDetails)
    }

    fun getUserDetails(mobileNumber: Long) : CommonResponse {
        return mainBusiness.getUserDetails(mobileNumber)
    }

    fun sendOtp(mobileNumber: Long): CommonResponse {
        return mainBusiness.sendOtp(mobileNumber)
    }

    fun verifyOtp(otp: Int, mobileNumber: Long): CommonResponse {
        return mainBusiness.verifyOtp(otp, mobileNumber)
    }

}