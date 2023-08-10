package com.example.androidcomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.androidcomponents.databinding.FragmentElementListBinding
import com.example.androidcomponents.interfaces.ListViewModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class ElementListFragment : Fragment() {

    private val sharedViewModel: ListViewModel by viewModels({ requireActivity() }) {
        SharedViewModel.provideFactory(PlaceholderContent, requireActivity().applicationContext)
    }
    private val adapter by lazy {
        MyListRecyclerViewAdapter(
            onClickListener = fragment
        )
    }

    private var binding: FragmentElementListBinding? = null

    private val fragment: (String) -> Unit = { id ->
        sharedViewModel.setId(id.toInt())
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.apply {
            replace(
                R.id.fragment,
                InfoElementFragment()
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
    }

    private fun initUi() {
        binding?.elementsList?.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            sharedViewModel.listState.collect { values ->
                adapter.setData(values.list)
            }
        }
    }
}