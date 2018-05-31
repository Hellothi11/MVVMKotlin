package com.mvvmdemo.mangalist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.nguyentamthi.mvvmdemo.databinding.ItemMangaBinding

/**
 * Created by thint on 5/27/18.
 */
class MangaListAdapter(
        private var mValues:List<MangaItem>,
        private val mMangaListViewModel: MangaListViewModel
) : RecyclerView.Adapter<MangaListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMangaBinding.inflate(inflater, parent, false)

        object : MangaItemActionListener {
            override fun onMangaClicked(manga: MangaItem) {
                mMangaListViewModel.clickedManga.value = manga
            }

        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bind(item)
    }

    inner class ViewHolder : RecyclerView.ViewHolder {

        private val mBinding:ItemMangaBinding

        constructor(binding: ItemMangaBinding) : super(binding.root){
            mBinding = binding
        }

        fun bind(item:MangaItem) {
            with(mBinding) {
                manga = item
                executePendingBindings()
            }
        }
    }

    fun replaceData(list: List<MangaItem>) {
        setList(list)
    }

    private fun setList(list: List<MangaItem>) {
        this.mValues = list
        notifyDataSetChanged()
    }
}