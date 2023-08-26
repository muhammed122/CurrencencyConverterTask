package com.qpn.kamashka.utils.extentions

import android.util.Patterns
import com.qpn.kamashka.utils.datetime.DateHelper.getCurrentYearUsingCalendar
import com.qpn.kamashka.utils.datetime.DateHelper.getYearFromSubmittedDate
import java.util.regex.Pattern


fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS
    .matcher(this).matches()


fun String.isValidPassword(passwordRegex: String?): Boolean {
    val passwordPattern: String = passwordRegex ?: ""
    val pattern = Pattern.compile(passwordPattern)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}


fun String.isValidAge(validAge: Int = 10): Boolean {
    if (getYearFromSubmittedDate(this) != null
        && getCurrentYearUsingCalendar() - getYearFromSubmittedDate(this)!!
        >= validAge
    )
        return true
    return false
}

fun String.isPhoneNumberValid(countryCode: String?): Boolean {
    val pattern = when (countryCode) {
        "KWT" -> Regex("^(\\+965|965)?[569]\\d{7}\$") // Kwait country
        // Add more patterns for other countries as needed
        else -> return false // Unknown country code
    }

    return pattern.matches(this)
}