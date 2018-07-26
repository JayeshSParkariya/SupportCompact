package org.supportcompactsample.test

import com.android.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.activity_base.*
import org.supportcompact.ActivityViewModel
import org.supportcompact.CoreActivity
import org.supportcompact.adapters.setFragmentPagerAdapter
import org.supportcompact.adapters.setPageAdapter
import org.supportcompact.ktx.confirmationDialog
import org.supportcompactsample.R
import org.supportcompactsample.databinding.ActivityBaseBinding
import org.supportcompactsample.databinding.PagerItemBinding

class BaseActivity : CoreActivity<ActivityViewModel, ActivityBaseBinding>() {

    override fun getLayout(): Int {
        return R.layout.activity_base
    }

    override fun createViewModel(): Class<out ActivityViewModel> {
        return ActivityViewModel::class.java
    }

    override fun setVM(binding: ActivityBaseBinding) {
        binding.vm = getViewModel()
        binding.executePendingBindings()
    }

    override fun createReference() {
        setUpFragmentPages()
    }

    private fun setUpPager() {
        val data = arrayListOf("One", "Two", "Three", "Four", "Five", "Six")
        mPager.setPageAdapter(R.layout.pager_item, data) { binder: PagerItemBinding, item: String ->
            binder.setVariable(BR.vm, item)
            binder.executePendingBindings()
        }
        mTabs.setupWithViewPager(mPager, true)
    }

    private fun setUpFragmentPages() {
        val fragments = arrayListOf(
                PagesFragment.newInstance("One"),
                PagesFragment.newInstance("Two"),
                PagesFragment.newInstance("Three"),
                PagesFragment.newInstance("Four"),
                PagesFragment.newInstance("Five"),
                PagesFragment.newInstance("Six")
        )
        mPager.setFragmentPagerAdapter(supportFragmentManager, fragments)
        mTabs.setupWithViewPager(mPager, true)
    }

    override fun onBackPressed() {
        confirmationDialog(
                msg = "Are you sure? You want exit.",
                btnPositiveClick = { super.onBackPressed() }
        )
    }
}