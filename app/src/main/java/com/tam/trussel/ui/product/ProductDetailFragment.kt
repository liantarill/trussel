package com.tam.trussel.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.tam.trussel.R
import com.tam.trussel.databinding.FragmentProductDetailBinding



class ProductDetailFragment : Fragment() {
    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val price = arguments?.getString("price")
        val location = arguments?.getString("location")
        val imageRes = arguments?.getInt("imageRes") ?: 0
        val condition = arguments?.getString("condition")
        val category = arguments?.getString("category")
        val color = arguments?.getString("color")
        val storage = arguments?.getString("storage")
        val isNegotiable = arguments?.getBoolean("isNegotiable")
        val description = arguments?.getString("description")
        val postedDate = arguments?.getString("postedDate")
        val viewsCount = arguments?.getString("viewsCount")

        val sellerName = arguments?.getString("sellerName")
        val sellerVerified = arguments?.getBoolean("sellerVerified")
        val sellerRating = arguments?.getFloat("sellerRating")
        val sellerReviews = arguments?.getInt("sellerReviews")
        val sellerSold = arguments?.getInt("sellerSold")
        val sellerListed = arguments?.getInt("sellerListed")
        val sellerJoin = arguments?.getString("sellerJoin")
        val sellerProfile = arguments?.getInt("sellerProfile") ?: 0

        val imageDetail1 = arguments?.getInt("imageDetail1") ?: 0

        // Set product information
        binding.textViewProductName.text = name
        binding.textViewCondition.text = condition
        binding.textViewPrice.text = "Rp $price"
        binding.textViewLocation.text = location
        binding.textViewPostedDate.text = postedDate
        binding.textViewViewsCount.text = viewsCount
        binding.textViewDescription.text = description
        binding.imageViewProduct.setImageResource(imageRes)

        // Set product details
        binding.textViewCategory.text = category
        binding.textViewColor.text = color
        binding.textViewStorage.text = storage
        binding.textViewNegotiable.text = if (isNegotiable == true) "Ya" else "Tidak"
        binding.textViewNegotiable.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                if (isNegotiable == true) R.color.green_600 else R.color.gray_500
            )
        )

        // Set seller information
        binding.textViewSellerName.text = sellerName
        binding.imageViewSeller.setImageResource(sellerProfile)

        // Show/hide verified badge based on seller verification status
        binding.textViewSellerVerified.visibility = if (sellerVerified == true) View.VISIBLE else View.GONE

        // Set seller rating
//        binding.textViewSellerRatingStars.text = getRatingStars(sellerRating ?: 0f)
        val starDrawables = getRatingStarDrawables(sellerRating ?: 0f)
        binding.starContainer.removeAllViews()
        for (drawable in starDrawables) {
            val starImage = ImageView(requireContext())
            starImage.setImageResource(drawable)
            val params = ViewGroup.MarginLayoutParams(48, 48) // Ukuran bisa disesuaikan
            params.setMargins(4, 0, 4, 0)
            starImage.layoutParams = params
            binding.starContainer.addView(starImage)
        }

        binding.textViewSellerRatingText.text = "(${sellerRating?.toString()?.take(3)} • ${sellerReviews} ulasan)"

        // Set seller stats
        binding.textViewSoldCount.text = sellerSold.toString()
        binding.textViewListedCount.text = sellerListed.toString()
        binding.textViewJoinDate.text = sellerJoin

        // Set click listeners
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonFavorite.setOnClickListener {
            // Handle favorite button click
        }

        binding.buttonChat.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_detail_product_to_navigaton_chat_person)
        }

        binding.buttonOffer.setOnClickListener {
            // Handle offer button click
        }

        binding.buttonShare.setOnClickListener {
            // Handle share button click
        }

        binding.buttonBookmark.setOnClickListener {
            // Handle bookmark button click
        }

        binding.buttonLike.setOnClickListener {
            // Handle like button click
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
//
//private fun getRatingStars(rating: Float): String {
//    val fullStars = rating.toInt()
//    val hasHalfStar = rating - fullStars >= 0.5f
//    val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0
//
//    return "★".repeat(fullStars) + if (hasHalfStar) "½" else "" + "☆".repeat(emptyStars)
//}


private fun getRatingStarDrawables(rating: Float): List<Int> {
    val fullStars = rating.toInt()
    val hasHalfStar = rating - fullStars >= 0.5f
    val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0

    val stars = mutableListOf<Int>()
    repeat(fullStars) { stars.add(R.drawable.star) }
    if (hasHalfStar) stars.add(R.drawable.star_half)
    repeat(emptyStars) { stars.add(R.drawable.star_empty) }
    return stars
}