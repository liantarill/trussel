package com.tam.trussel.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tam.trussel.R
import com.tam.trussel.ui.product.Product
import com.tam.trussel.ui.product.ProductAdapter

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProducts)
        val products = getSampleProducts()

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.adapter = ProductAdapter(products)

        val itemHeightDp = 350
        val scale = resources.displayMetrics.density
        val itemHeightPx = (itemHeightDp * scale).toInt()

        val totalRows = (products.size + 1) / 2
        val totalHeight = itemHeightPx * totalRows

        recyclerView.layoutParams = recyclerView.layoutParams.apply {
            height = totalHeight
        }

        return view
    }

    private fun getSampleProducts(): List<Product> {
        return listOf(
            Product(
                name = "iPhone 13 Pro Max",
                price = "8.500.000",
                location = "Jakarta Selatan",
                imageRes = R.drawable.iphone,
                condition = "Like New",
                category = "Elektronik",
                color = "Pacific Blue",
                storage = "128GB",
                isNegotiable = true,
                description = "iPhone 18 Pro Max 128GB warna Pacific Blue dalam kondisi sangat baik. Tidak ada lecet atau kerusakan. Lengkap dengan box original, charger, dan earphone yang belum pernah dipakai.",
                postedDate = "1 week ago",
                viewsCount = "1.2k views",
                seller = Product.Seller(
                    name = "Ahmad Rizki",
                    isVerified = true,
                    rating = 4.8f,
                    reviewsCount = 127,
                    soldItems = 89,
                    listedItems = 12,
                    joinDate = "2 tahun",
                    profileImageRes = R.drawable.person
                ),
                images = listOf(R.drawable.iphone, R.drawable.iphone, R.drawable.iphone)
            ),
            Product(
                name = "Sepeda Trek",
                price = "12.500.000",
                location = "Bandung",
                imageRes = R.drawable.sepedah,
                condition = "Bekas",
                category = "Olahraga",
                color = "Hitam Merah",
                storage = "-",
                isNegotiable = false,
                description = "Sepeda Trek kondisi 90%, jarang dipakai, selalu disimpan di dalam ruangan.",
                postedDate = "3 days ago",
                viewsCount = "542 views",
                seller = Product.Seller(
                    name = "Budi Santoso",
                    isVerified = true,
                    rating = 4.5f,
                    reviewsCount = 89,
                    soldItems = 45,
                    listedItems = 8,
                    joinDate = "1.5 tahun",
                    profileImageRes = R.drawable.person
                ),
                images = listOf(R.drawable.sepedah, R.drawable.sepedah)
            ),
            Product(
                name = "Nike P-6000",
                price = "12.999.000",
                location = "Surabaya",
                imageRes = R.drawable.p6000,
                condition = "Baru",
                category = "Fashion",
                color = "Putih",
                storage = "42",
                isNegotiable = true,
                description = "Sepatu Nike P-6000 original, baru beli tapi ukuran tidak pas.",
                postedDate = "5 days ago",
                viewsCount = "876 views",
                seller = Product.Seller(
                    name = "Citra Dewi",
                    isVerified = false,
                    rating = 4.2f,
                    reviewsCount = 34,
                    soldItems = 12,
                    listedItems = 5,
                    joinDate = "8 bulan",
                    profileImageRes = R.drawable.person
                ),
                images = listOf(R.drawable.p6000, R.drawable.p6000)
            ),
            Product(
                name = "ASUS ROG Phone",
                price = "9.000.000",
                location = "Yogyakarta",
                imageRes = R.drawable.g_pixel,
                condition = "Bekas",
                category = "Elektronik",
                color = "Hitam",
                storage = "256GB",
                isNegotiable = true,
                description = "ASUS ROG Phone 7 Ultimate, performa gaming terbaik, baterai masih sehat.",
                postedDate = "2 weeks ago",
                viewsCount = "1.5k views",
                seller = Product.Seller(
                    name = "Doni Pratama",
                    isVerified = true,
                    rating = 4.9f,
                    reviewsCount = 215,
                    soldItems = 132,
                    listedItems = 24,
                    joinDate = "3 tahun",
                    profileImageRes = R.drawable.person
                ),
                images = listOf(R.drawable.g_pixel, R.drawable.g_pixel)
            ),
            Product(
                name = "PS5",
                price = "6.500.000",
                location = "Depok",
                imageRes = R.drawable.ps5,
                condition = "Like New",
                category = "Gaming",
                color = "Putih",
                storage = "825GB",
                isNegotiable = false,
                description = "PlayStation 5 Digital Edition, lengkap dengan controller dan kabel, kondisi seperti baru.",
                postedDate = "1 day ago",
                viewsCount = "2.3k views",
                seller = Product.Seller(
                    name = "Eko Wijaya",
                    isVerified = true,
                    rating = 4.7f,
                    reviewsCount = 178,
                    soldItems = 94,
                    listedItems = 15,
                    joinDate = "2.5 tahun",
                    profileImageRes = R.drawable.person
                ),
                images = listOf(R.drawable.ps5, R.drawable.ps5)
            )
        )
    }
}