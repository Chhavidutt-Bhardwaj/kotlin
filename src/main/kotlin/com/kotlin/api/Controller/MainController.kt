package com.kotlin.api.Controller

import com.kotlin.api.CommonResponse
import com.kotlin.api.Model.UserDetails
import com.kotlin.api.Service.MainService
import com.kotlin.api.Utility.CommonUtility
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Controller
class MainController(val mainService: MainService, val commonUtility: CommonUtility) {

    private final val logger = LoggerFactory.getLogger(MainController::class.java)

    @PostMapping("/addUserDetails")
    fun addUserDetails(@RequestBody userDetails: UserDetails): ResponseEntity<CommonResponse> {

        val response : CommonResponse = mainService.addUserDetail(userDetails)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }

    @GetMapping("/getUserDetails")
    fun getUserDetails(@RequestParam mobileNumber: Long): ResponseEntity<CommonResponse> {
        val response : CommonResponse = mainService.getUserDetails(mobileNumber)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }

    @PostMapping("/sendOtp")
    fun sendOtp(@RequestParam("mobileNumber") mobileNumber: Long) :ResponseEntity<CommonResponse>{
        logger.info("... In MainController ...")
        logger.info("... in sendOtp... $mobileNumber")
        val response : CommonResponse = mainService.sendOtp(mobileNumber)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }

    @PostMapping("/verifyOtp")
    fun verifyOtp(@RequestParam("otp") otp : Int, @RequestParam("mobileNumber") mobileNumber: Long) : ResponseEntity<CommonResponse> {
        logger.info("... In MainController ...")
        logger.info("... in verifyOtp  $mobileNumber")
        val response : CommonResponse = mainService.verifyOtp(otp, mobileNumber)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }
}
