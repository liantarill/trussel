package com.tam.trussel

//data class User(
//    val username: String,
//    val email: String,
//    val phone: String,
//    val password: String,
//    val agreedToTerms: Boolean,
//    val subscribeToNewsletter: Boolean
//)

data class User(
    val email: String ="",
    val password: String ="",
    val username: String = "",
    val phone: String = "",
    val isVerified: Boolean = false
) {
    // Validasi email dan password
    fun isValid(): Boolean {
        return email.isValidEmail() && password.isValidPassword()
    }

    // Extension function untuk validasi email
    private fun String.isValidEmail(): Boolean {
        return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    // Extension function untuk validasi password
    private fun String.isValidPassword(): Boolean {
        return this.length >= 1
    }
}