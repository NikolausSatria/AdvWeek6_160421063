package com.example.advweek6_160421063.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.advweek6_160421063.R
import com.example.advweek6_160421063.databinding.FragmentBrandDetailBinding
import com.example.advweek6_160421063.databinding.FragmentBrandListBinding
import com.example.advweek6_160421063.viewmodel.ListViewModel
import com.squareup.picasso.Picasso

class BrandDetailFragment : Fragment() {
    private lateinit var  binding: FragmentBrandDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBrandDetailBinding.inflate(inflater, container, false)
        return  binding.root
    }

}