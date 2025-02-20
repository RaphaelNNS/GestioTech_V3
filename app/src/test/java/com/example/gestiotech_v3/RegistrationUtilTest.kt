package com.example.gestiotech_v3

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{
    @Test
    fun `empty username returns false`() {
        val result = RegistrationUtil.validateRegistration(
            "",
            "123",
            "123"
        )
        assertThat(result).isFalse()
    }

}