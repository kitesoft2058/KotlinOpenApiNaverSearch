package com.kitesoft.kotlinopenapinaversearch

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kitesoft.kotlinopenapinaversearch.databinding.RecyclerItemBinding

class MyAdapter(val context: Context, var items:MutableList<ShoppingItem>) : RecyclerView.Adapter<MyAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(RecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {

        var title= HtmlCompat.fromHtml(items[position].title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        holder.binding.tvTitle.setText(title)

        holder.binding.tvLowPrice.setText("${items[position].lprice}원")
        holder.binding.tvBrand.setText(items[position].brand)
        Glide.with(context).load(items[position].image).error(R.drawable.g_07).into(holder.binding.iv)

        holder.binding.root.setOnClickListener { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(items[position].link))) }
    }

    override fun getItemCount(): Int = items.size

    inner class VH(val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root)
}