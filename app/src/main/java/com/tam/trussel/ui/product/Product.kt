package com.tam.trussel.ui.product

data class Product(
    val name: String,
    val price: String,
    val location: String,
    val imageRes: Int,
    val condition: String,  // Like New, etc.
    val category: String,   // Elektronik, etc.
    val color: String,      // Pacific Blue, etc.
    val storage: String,    // 128GB, etc.
    val isNegotiable: Boolean, // Bisa nego or not
    val description: String,
    val postedDate: String, // 1 week ago
    val viewsCount: String, // 1.2k views
    val seller: Seller,
    val images: List<Int> = emptyList() // For ViewPager images
) {
    data class Seller(
        val name: String,
        val isVerified: Boolean,
        val rating: Float,      // 4.8
        val reviewsCount: Int,   // 127
        val soldItems: Int,      // 89
        val listedItems: Int,    // 12
        val joinDate: String,    // 2 tahun
        val profileImageRes: Int
    )
}