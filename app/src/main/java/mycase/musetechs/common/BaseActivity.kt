package mycase.musetechs.common


import android.os.Bundle
import androidx.annotation.LayoutRes

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseActivity<DB:ViewDataBinding,VM:ViewModel>:AppCompatActivity() {

    lateinit var dataBinding:DB
    lateinit var viewModel: VM

    @LayoutRes
    abstract fun getLayoutRes():Int

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,getLayoutRes())
        viewModel = ViewModelProviders.of(this).get(getViewModel())

    }
}