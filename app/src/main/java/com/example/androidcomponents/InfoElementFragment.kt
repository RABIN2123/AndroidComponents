package com.example.androidcomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidcomponents.databinding.FragmentInfoElementBinding
import com.example.androidcomponents.interfaces.InfoViewModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.launch


class InfoElementFragment : Fragment() {
    private val viewModel: InfoViewModel by viewModels({ requireActivity() }) {
        SharedViewModel.provideFactory(PlaceholderContent, requireActivity().applicationContext)
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
        showInfo()

    }

    private fun showInfo() {
        viewModel.getInfo()
        lifecycleScope.launch {
            viewModel.listState.collect { items ->
                values = items.info
            }
        }
        values?.let {
            binding?.apply {
                idEditText.setText(it.id)
                nameEditText.setText(it.content)
                descriptionEditText.setText(it.details)
            }
        }

    }
}