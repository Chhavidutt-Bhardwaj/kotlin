package com.kotlin.api.Utility

import com.kotlin.api.CommonResponse
import org.springframework.stereotype.Component
import java.util.*

@Component
class CommonUtility {
    companion object {
        fun getCommonResponse(msg: String, objects: Any): CommonResponse {
            var commonResponse = CommonResponse(msg, objects) // Initialize and create a new instance
            return commonResponse // Return the modified commonResponse
        }

        fun randomAlphaNumeric(count: Int): String {
            val builder = StringBuilder()
            val random = java.util.Random()

            for (i in 0 until count) {
                val character = Constant.ALPHA_NUMERIC_STRING[random.nextInt(Constant.ALPHA_NUMERIC_STRING.length)]
                builder.append(character)
            }
            return builder.toString()
        }

        fun  getDoctorCode(id: String?): String? {
            var modifiedId = id
            if (modifiedId.equals("CP", ignoreCase = true)) {
                modifiedId = "CP0"
            }
            if (!modifiedId.isNullOrEmpty() && modifiedId.contains("CP")) {
                val splitId = modifiedId.split("CP")[1]
                val number = splitId.toIntOrNull() ?: return null
                val nextNumber = number + 1
                return when {
                    nextNumber < 10 -> "CP000$nextNumber"
                    nextNumber < 100 -> "CP00$nextNumber"
                    nextNumber < 1000 -> "CP0$nextNumber"
                    else -> "CP$nextNumber"
                }
            }
            return null
        }

        fun generate4DigitOTP(): Int {
            val random = Random()
            return 1000 + random.nextInt(9000)
        }

    }
}