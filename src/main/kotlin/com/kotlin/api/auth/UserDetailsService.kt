package com.kotlin.api.auth

import com.kotlin.api.DAO.DoctorDAO
import com.kotlin.api.Model.DoctorPersonalDO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsService : UserDetailsService {
    @Autowired
    private val repository: DoctorDAO? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(phoneNo: String): UserDetails? {
        var doctorDetail: DoctorPersonalDO? = repository?.findByMobileNo(phoneNo)
        if (doctorDetail != null) {
            return User(
                doctorDetail.phoneNumber, "",
                ArrayList()
            )
        }
        return null
    }
}