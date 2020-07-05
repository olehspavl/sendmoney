package com.sendmoney.sendmoney.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sendmoney.sendmoney.R
import com.sendmoney.sendmoney.databinding.FeatureSendmoneyMainFragmentBinding
import com.sendmoney.sendmoney.loadModule

class MainFragment : Fragment(R.layout.feature_sendmoney_main_fragment) {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FeatureSendmoneyMainFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadModule()
    }

    /*override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FeatureSendmoneyMainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FeatureSendmoneyMainFragmentBinding.inflate(layoutInflater)
        view.findViewById<Button>(R.id.action).setOnClickListener {
            Log.e("asdasd", "as!!dasd")
            findNavController().navigate(MainFragmentDirections.toOptions())
        }
        initUI()
    }

    private fun initUI() {
//        binding.toolbar.setupWithNavController(findNavController())
//        binding.action.setOnClickListener {
//            Log.e("asdasd", "as22dasd")
//            findNavController().navigate(MainFragmentDirections.toOptions())
//        }
    }
}