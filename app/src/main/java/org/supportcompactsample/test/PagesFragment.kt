package org.supportcompactsample.test


import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.fragment_pages.*
import org.supportcompact.CoreFragment
import org.supportcompact.adapters.setUpRecyclerView
import org.supportcompactsample.R
import org.supportcompactsample.databinding.FragmentPagesBinding
import org.supportcompactsample.databinding.PagerItemBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"


/**
 * A simple [Fragment] subclass.
 * Use the [PagesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PagesFragment : CoreFragment<PageVM, FragmentPagesBinding>() {

    override fun getLayout(): Int {
        return R.layout.fragment_pages
    }

    override fun createViewModel(): Class<out PageVM> {
        return PageVM::class.java
    }

    override fun setVM(binding: FragmentPagesBinding) {
        binding.page = getViewModel()
        binding.executePendingBindings()
    }

    override fun createReference() {
        arguments?.let {
            getViewModel().title.set(it.getString(ARG_PARAM1))
        }
        val data = arrayListOf("One", "Two", "Three", "Four", "Five", "Six")
        mRecyclerView.setUpRecyclerView(R.layout.pager_item, data) { item: String, binder: PagerItemBinding, position: Int ->
            binder.vm = item
            binder.executePendingBindings()
        }
    }

    override fun toString(): String {
        arguments?.let {
            return it.getString(ARG_PARAM1)
        }
        return super.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment PagesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String) = PagesFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }
}
