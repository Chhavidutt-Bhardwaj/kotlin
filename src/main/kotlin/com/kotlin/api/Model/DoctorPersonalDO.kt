package com.kotlin.api.Model

import com.kotlin.api.enum.DoctorType
import com.kotlin.api.enum.FormStatus
import jakarta.persistence.*
import java.time.LocalDate
import java.util.*
@Entity
@Table(name = "doctor_personal_detail")
data class DoctorPersonalDO(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int = 0,
    @Column(name = "doctor_id")
    var doctorId : String = "",
    @Column(name = "doctor_name")
    var doctorName : String = "",
    @Column(name = "doctor_code")
    var doctorCode : String = "",
    @Column(name = "email_id")
    var emailId  : String = "",
    @Column(name = "date_of_birth")
    var dob : Date? = null,
    @Column(name = "phone_number")
    var phoneNumber : String = "",
    @Column(name = "pan_number")
    var panNo : String = "",
    @Enumerated(EnumType.STRING)
    @Column(name = "form_status")
    var formStatus : FormStatus? = null,
    @Column(name = "qr_code_url")
    var qrCodeUrl : String = "",
    @Column(name = "verified")
    var verified : Boolean = false,
    @Column(name = "important")
    var important : Boolean = false,
    @Column(name = "joining_date")
    var joiningDate : Date? = null,
    @Column(name = "scout_code")
    var scoutCode : String = "",
    @Column(name = "qr_installation_status")
    var qrInstallationStatus : Boolean = false,
    @Enumerated(EnumType.STRING)
    @Column(name = "doctor_status")
    var doctorStatus: DoctorType? = null,
    @Column(name = "update_in")
    var updatedIn : String = "",
    @Column(name = "updated_on")
    var updatedOn : Date? = null
)
