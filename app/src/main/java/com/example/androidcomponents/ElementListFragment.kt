package com.example.androidcomponents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidcomponents.databinding.FragmentElementListBinding
import com.example.androidcomponents.models.ElementListModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.presenters.ElementListPresenter
import com.example.androidcomponents.presenters.interfaces.ElementListView

/**
 * A fragment representing a list of Items.
 */
class ElementListFragment() : Fragment(),
    ElementListView<List<PlaceholderContent.PlaceholderItem>> {


    private val adapter by lazy {
        MyListRecyclerViewAdapter(
            onClickListener = fragment
        )
    }
    private val presenter by lazy {
        ElementListPresenter(model)
    }
    private val model by lazy {
        ElementListModel()
    }

    private var binding: FragmentElementListBinding? = null

    private val fragment: (PlaceholderContent.PlaceholderItem) -> Unit = { item ->
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.apply {
            replace(
                R.id.fragment,
                InfoElementFragment.newInstance(item.id)
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
        presenter.attachView(this)
        presenter.viewIsReady()

    }

    override fun showInfo(values: List<PlaceholderContent.PlaceholderItem>) {
        adapter.setData(values)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}