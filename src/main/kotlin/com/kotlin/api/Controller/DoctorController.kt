package com.kotlin.api.Controller

import com.kotlin.api.CommonResponse
import com.kotlin.api.POJO.DoctorDetailsPO
import com.kotlin.api.Service.DoctorService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam

@Controller("/doctor")
class DoctorController(private val doctorService : DoctorService) {

    private final val logger = LoggerFactory.getLogger(DoctorController::class.java)

    @PostMapping("/saveOrUpdateDoctorPersonalDetails")
    fun saveOrUpdateDoctorPersonalDetails(@RequestBody doctorDetailsPO: DoctorDetailsPO) : ResponseEntity<CommonResponse> {
        logger.info("... IN DoctorController ...")
        logger.info("... in saveOrUpdateDoctorPersonalDetails ...")
        val response = doctorService.saveOrUpdateDoctorPersonalDetails(doctorDetailsPO)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }

    @GetMapping("/getDoctorPersonalDetail")
    fun getDoctorPersonalDetail(@RequestParam("doctorId", required = false, defaultValue = "") doctorId : String,
                                @RequestParam("doctorCode", required = false, defaultValue = "") doctorCode : String) : ResponseEntity<CommonResponse>{
        logger.info("... IN DoctorController ...")
        logger.info("... in saveOrUpdateDoctorPersonalDetails ...")
        val response = doctorService.getDoctorPersonalDetail(doctorId,doctorCode)
        return ResponseEntity.status(HttpStatus.valueOf(response.message)).body(response)
    }
}