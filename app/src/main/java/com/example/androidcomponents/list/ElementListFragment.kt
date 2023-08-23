package com.example.androidcomponents.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidcomponents.infoscreen.InfoElementFragment
import com.example.androidcomponents.R
import com.example.androidcomponents.databinding.FragmentElementListBinding
import kotlinx.coroutines.launch


class ElementListFragment() : Fragment() {

    private val adapter by lazy {
        MyListRecyclerViewAdapter(
            onClickListener = fragment
        )
    }

    private val listViewModel: ListViewModel by viewModels {
        ListViewModel.provideFactory(
            interactors = setOf(
                LoadListInteractor()
            )
        )
    }

    private var binding: FragmentElementListBinding? = null

    private val fragment: (String) -> Unit = { id ->
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.apply {
            replace(
                R.id.fragment,
                InfoElementFragment(id.toInt())
            )
            addToBackStack(null)
            commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentElementListBinding.inflate(LayoutInflater.from(context), container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observeViewModel()
        listViewModel.loadData()
    }

    private fun initUi() {
        binding?.elementsList?.adapter = adapter
    }
    private fun observeViewModel() {
        lifecycleScope.launch {
            listViewModel.listState.collect { values ->
                adapter.setData(values.list)
            }
        }

    }
}