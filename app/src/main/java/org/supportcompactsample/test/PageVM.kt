package org.supportcompactsample.test

import android.databinding.ObservableArrayList
import org.supportcompact.FragmentViewModel
import org.supportcompact.ktx.dismissProgress
import org.supportcompact.ktx.postError
import org.supportcompact.ktx.showProgress
import org.supportcompact.networking.ApiClient
import org.supportcompact.networking.SingleCallback
import org.supportcompact.networking.subscribeToSingle
import org.supportcompactsample.apis.WebserviceBuilder
import org.supportcompactsample.apis.models.Todo

class PageVM : FragmentViewModel(), SingleCallback<WebserviceBuilder.ApiNames> {

    val todoList = ObservableArrayList<Todo>()

    init {
        getTodoList()
    }

    private fun getTodoList() {
        showProgress()
        subscribeToSingle(
                ApiClient.getApiClient().create(WebserviceBuilder::class.java).getTodos(),
                WebserviceBuilder.ApiNames.GetTodoList,
                this@PageVM
        )
    }

    override fun onSingleSuccess(o: Any?, apiNames: WebserviceBuilder.ApiNames) {
        dismissProgress()
        when (apiNames) {
            WebserviceBuilder.ApiNames.GetTodoList -> {
                if (o is List<Any?>) {
                    todoList.addAll(o as Collection<Todo>)
                } else {
                    postError("Something went wrong. Please try again latter")
                }
            }
        }
    }

    override fun onFailure(throwable: Throwable, apiNames: WebserviceBuilder.ApiNames) {
        throwable.postError()
    }
}