package com.example.comcasttest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comcasttest.databinding.FlavorFragmentBinding
import com.example.comcasttest.model.Repository
import com.example.comcasttest.model.RepositoryImpl
import com.example.comcasttest.model.Response
import com.example.comcasttest.model.Topic
import com.example.comcasttest.view.adapter.FlavorAdapter
import com.example.comcasttest.viewmodel.FlavorViewModel

class FlavorFragment: Fragment() {

    private val repository: Repository by lazy {
        RepositoryImpl()
    }

    private val viewModel: FlavorViewModel by lazy {
        FlavorViewModel.FlavorViewModelProvider(repository).create(
            FlavorViewModel::class.java
        )
    }

    private lateinit var binding: FlavorFragmentBinding

    private lateinit var adapter: FlavorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FlavorFragmentBinding.inflate(inflater)

        initViews()

        viewModel.liveData.observe(viewLifecycleOwner,
            {response->
                when(response){
                    is RepositoryImpl.AppState.SUCCESS-> updateDataSet(response.data)
                    is RepositoryImpl.AppState.ERROR -> showErrorMessage(response.errorMessage)
                    is RepositoryImpl.AppState.LOADING -> isLoading(true)
                }
            })
        return binding.root
    }

    private fun initViews() {
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        adapter = FlavorAdapter(null, ::createDetails)
        binding.recyclerview.adapter = adapter
    }

    private fun createDetails(topic: Topic) {
        Toast.makeText(context, "${topic.Text}", Toast.LENGTH_SHORT).show();
    }

    override fun onStart() {
        super.onStart()
        viewModel.getData()
    }

    private fun isLoading(loading: Boolean) {
        if(loading) binding.loading.visibility = View.VISIBLE
        else binding.loading.visibility = View.GONE
    }

    private fun showErrorMessage(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun updateDataSet(data: Response?) {
        isLoading(false)
        adapter.setDataSet(data)
    }
}