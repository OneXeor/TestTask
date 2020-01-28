/*
 * Copyright (c) OneXeor 2019.
 */

package by.onexeor.testtask.ui.base

import android.os.Handler
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: ArrayList<T?>? = ArrayList()

    var listObserver: ((ArrayList<T?>?) -> Unit)? = null

    private val observerHandler: Handler = Handler()

    final override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return onCreateDefaultViewHolder(parent, viewType)
    }

    abstract fun onCreateDefaultViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder

    final override fun getItemCount() = items?.size ?: 0

    open fun setData(data: List<T?>?) {
        if (!data.isNullOrEmpty()) {
            items?.clear()
            items = data as ArrayList<T?>?
            notifyDataSetChanged()
        }
        notifyListObserver()
    }

    open fun add(response: T?) {
        items?.add(response)
        items?.size?.minus(1)?.let { notifyItemInserted(it) }
    }

    open fun addAll(items: List<T?>?) {
        if (items != null) {
            for (response in items) {
                response?.let { add(it) }
            }
        }
        notifyListObserver()
    }

    fun remove(item: T) {
        val position = items?.indexOf(item)
        if (position != null) {
            if (position > -1) {
                items?.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }


    fun removeAt(position: Int) {
        if (position > -1) {
            items?.removeAt(position)
            notifyItemRemoved(position)
        }
        notifyListObserver()
    }

    fun clear() {
        while (itemCount > 0) {
            getItem(0)?.let { remove(it) }
        }
        notifyListObserver()
    }

    fun getItem(position: Int): T? {
        return items?.get(position)
    }

    private fun notifyListObserver() {
        observerHandler.removeCallbacksAndMessages(null)
        observerHandler.postDelayed({
            listObserver?.let { it(this.items) }
        }, 600L)
    }
}