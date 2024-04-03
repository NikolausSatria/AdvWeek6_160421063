package com.example.advweek6_160421063.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.advweek6_160421063.databinding.BrandListItemBinding
import com.example.advweek6_160421063.model.Brand
import com.squareup.picasso.Picasso

class BrandListAdapter (val brandList: ArrayList<Brand>):RecyclerView.Adapter<BrandListAdapter.BrandViewHolder>(){
    class BrandViewHolder(var binding: BrandListItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = BrandListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BrandViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return brandList.size
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        with(holder.binding ){
            txtID.text = brandList[position].id
            txtName.text = brandList[position].name
            txtFounded.text = "From " + brandList[position].founded
            txtFounder.text = brandList[position].founder?.let { brandFounder->
                brandFounder.name + " (${brandFounder.birth_year})"
            }
        }

        brandList[position].products.let { product->
            holder.binding.txtProduct.text = product?.joinToString(" | ")
        }

        val url = brandList[position].images
        val builder = Picasso.Builder(holder.itemView.context)
        builder.listener { picasso, uri, exception ->  exception.printStackTrace() }
        Picasso.get().load(url).into(holder.binding.imageView)
    }

    fun updateBrandList(newBrandList: ArrayList<Brand>){
        brandList.clear()
        brandList.addAll(newBrandList)
        notifyDataSetChanged()
    }

}