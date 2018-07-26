package org.supportcompact.adapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import org.supportcompact.ktx.inflate

abstract class MultiViewRecyclerAdapter<T : WidgetsViewModel>(private val list: List<T>) : RecyclerView.Adapter<MultiViewRecyclerAdapter.ViewHolder<ViewDataBinding>>() {

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<ViewDataBinding> {
        return ViewHolder(DataBindingUtil.bind(parent.inflate(viewType)))
    }

    override fun onBindViewHolder(holder: ViewHolder<ViewDataBinding>, position: Int) {
        val model = list[position]
        holder.binding?.setVariable(getBindingVariable(), model)
        holder.binding?.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    abstract fun getBindingVariable(): Int

    class ViewHolder<out V : ViewDataBinding>(val binding: V?) : RecyclerView.ViewHolder(binding?.root)
}