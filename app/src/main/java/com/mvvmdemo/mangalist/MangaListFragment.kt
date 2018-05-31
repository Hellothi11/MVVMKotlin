package com.mvvmdemo.mangalist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.nguyentamthi.mvvmdemo.databinding.FragmentMangalistBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nguyentamthi.mvvmdemo.R
import com.mvvmdemo.common.obtainViewModel
import com.mvvmdemo.mangalist.dummy.DummyContent
import com.mvvmdemo.mangalist.dummy.DummyContent.DummyItem

class MangaListFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentMangalistBinding
    private lateinit var listAdapter: MangaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentMangalistBinding.inflate(inflater, container, false).apply {
            viewmodel = obtainViewModel(MangaListViewModel::class.java)
        }

        setupListAdapter()
        viewDataBinding.viewmodel?.start()
        return viewDataBinding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = MangaListAdapter(ArrayList(0), viewModel)
            viewDataBinding.mangaList.adapter = listAdapter
        } else {
            Log.w("MangaListFragment", "ViewModel not initialized when attempting to set up adapter.")
        }
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
