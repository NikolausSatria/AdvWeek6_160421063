package com.example.advweek6_160421063.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.advweek6_160421063.R
import com.example.advweek6_160421063.databinding.FragmentBrandListBinding
import com.example.advweek6_160421063.viewmodel.ListViewModel

class BrandListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val brandListAdapter = BrandListAdapter(arrayListOf())
    private lateinit var  binding: FragmentBrandListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrandListBinding.inflate(inflater, container, false)
        return  binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        with(binding){
            recView.layoutManager = LinearLayoutManager(context)
            recView.adapter = brandListAdapter
        }
        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.refresh()
            binding.refreshLayout.isRefreshing = false
        }
    }

    fun observeViewModel(){
        viewModel.brandsLD.observe(viewLifecycleOwner, Observer{
            brandListAdapter.updateBrandList(it)
        })
        viewModel.brandLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
            binding.txtError?.visibility = View.VISIBLE
        } else {
            binding.txtError?.visibility = View.GONE
        }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
            binding.recView.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
        } else {
            binding.recView.visibility = View.VISIBLE
            binding.progressLoad.visibility = View.GONE
        }
        })
    }

}