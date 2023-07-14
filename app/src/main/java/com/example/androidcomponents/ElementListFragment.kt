package com.example.androidcomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcomponents.databinding.FragmentElementListBinding
import com.example.androidcomponents.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ElementListFragment() : Fragment() {

    private val adapter by lazy {
        MyListRecyclerViewAdapter(
            values = PlaceholderContent.ITEMS,
            onClickListener = fragment
        )
    }
    private var binding: FragmentElementListBinding? = null
    private val fragment: (PlaceholderContent.PlaceholderItem) -> Unit = {item ->
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.apply {
            replace(R.id.fragment, InfoElementFragment.newInstance(item.id, item.content, item.details))
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
    }
}