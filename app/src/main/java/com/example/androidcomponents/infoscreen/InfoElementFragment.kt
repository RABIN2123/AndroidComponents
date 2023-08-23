package com.example.androidcomponents.infoscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidcomponents.databinding.FragmentInfoElementBinding
import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.launch


class InfoElementFragment(private val id: Int) : Fragment() {

    private val infoViewModel: InfoViewModel by viewModels {
        InfoViewModel.provideFactory(
            interactors = setOf(
                GetInfoInteractor()
            ),
            requireActivity().applicationContext
        )
    }
    private var binding: FragmentInfoElementBinding? = null
    private var values: PlaceholderContent.PlaceholderItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInfoElementBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        infoViewModel.loadInfo(id)
        observeInfo()
        showInfo()
    }

    private fun showInfo() {
        values?.let {

        }

    }
    private fun observeInfo() {
        lifecycleScope.launch {
            infoViewModel.listState.collect { items ->
                Log.d("TAG", "info ${items.info}")
                binding?.apply {
                    idEditText.setText(items.info.id)
                    nameEditText.setText(items.info.content)
                    descriptionEditText.setText(items.info.details)
                }
            }
        }
    }
}