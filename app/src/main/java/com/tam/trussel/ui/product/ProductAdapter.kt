package com.tam.trussel.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tam.trussel.R

class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val buttonFavorite: ImageButton = itemView.findViewById(R.id.buttonFavorite)
        val textViewCondition : TextView = itemView.findViewById(R.id.textViewCondition)
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
        val textViewLocation: TextView = itemView.findViewById(R.id.textViewLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentItem = productList[position]

        holder.imageViewProduct.setImageResource(currentItem.imageRes)
        holder.textViewCondition.text = currentItem.condition
        holder.textViewProductName.text = currentItem.name
        holder.textViewPrice.text = currentItem.price
        holder.textViewLocation.text = currentItem.location

        holder.buttonFavorite.setOnClickListener {
            // Handle favorite button click
        }

        // Optional: Set click listener for the whole item
        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putString("name", currentItem.name)
                putString("price", currentItem.price)
                putString("location", currentItem.location)
                putInt("imageRes", currentItem.imageRes)
                putString("condition", currentItem.condition)
                putString("category", currentItem.category)
                putString("color", currentItem.color)
                putString("storage", currentItem.storage)
                putBoolean("isNegotiable", currentItem.isNegotiable)
                putString("description", currentItem.description)
                putString("postedDate", currentItem.postedDate)
                putString("viewsCount", currentItem.viewsCount)

                // Sederhanakan seller
                putString("sellerName", currentItem.seller.name)
                putBoolean("sellerVerified", currentItem.seller.isVerified)
                putFloat("sellerRating", currentItem.seller.rating)
                putInt("sellerReviews", currentItem.seller.reviewsCount)
                putInt("sellerSold", currentItem.seller.soldItems)
                putInt("sellerListed", currentItem.seller.listedItems)
                putString("sellerJoin", currentItem.seller.joinDate)
                putInt("sellerProfile", currentItem.seller.profileImageRes)

                // Tidak bisa kirim list image langsung → kirim satu saja
                putInt("imageDetail1", currentItem.images.getOrNull(0) ?: 0)
            }

            androidx.navigation.Navigation.findNavController(it)
                .navigate(R.id.action_navigation_home_to_navigation_detail_product, bundle)
        }

    }

    //    override fun getItemCount() = productList.size
    override fun getItemCount(): Int {
        Log.d("ProductAdapter", "Total items: ${productList.size}")
        return productList.size
    }
}