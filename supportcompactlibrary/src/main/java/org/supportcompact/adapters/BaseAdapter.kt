package org.supportcompact.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import org.supportcompact.ktx.inflate

/**
 * Created by jayeshparkariya on 26/2/18.
 */

fun <T, U : ViewDataBinding> RecyclerView.setUpRecyclerView(@LayoutRes layoutRes: Int, itemList: ArrayList<T>, onBind: ((item: T, binder: U, position: Int) -> Unit)) = BaseAdapter(this, layoutRes, itemList, onBind)

class BaseAdapter<in T, U : ViewDataBinding>(recyclerView: RecyclerView, @LayoutRes private val layoutRes: Int, arrList: ArrayList<T>, private val onBind: (item: T, binder: U, position: Int) -> Unit) : RecyclerView.Adapter<BaseAdapter.ViewHolder<U>>() {

    private var listItem = arrList

    init {
        recyclerView.adapter = this
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<U> {
        return ViewHolder(DataBindingUtil.bind(parent.inflate(getLayout()))!!)
    }

    override fun onBindViewHolder(holder: ViewHolder<U>, position: Int) {
        val item = listItem[position]
        onBind.invoke(item, holder.binding, position)
    }

    class ViewHolder<out V : ViewDataBinding>(internal val binding: V) : RecyclerView.ViewHolder(binding.root)

    @LayoutRes
    private fun getLayout(): Int = layoutRes
}