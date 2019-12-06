package org.supportcompactsample.test

import androidx.lifecycle.MutableLiveData
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

    val todoList = MutableLiveData<ArrayList<Todo>>()

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
                    todoList.value = o as ArrayList<Todo>?
                } else {
                    postError("Something went wrong. Please try again latter")
                }
            }
        }
    }

    override fun onFailure(throwable: Throwable, apiNames: WebserviceBuilder.ApiNames) {
        throwable.postError()
    }

    /*fun updateProfile(): MutableLiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        showProgress()
        val dataMap = HashMap<String, RequestBody?>()
        dataMap["designation"] = App.INSTANCE.getUserType().toReqBoday()
        dataMap["name"] = name.get()?.toReqBoday()
        dataMap["date_of_birth"] = dob.get()?.toReqBoday()
        dataMap["shop_name"] = garage.get()?.toReqBoday()
        dataMap["shipping_address[line_1]"] = address1.get()?.toReqBoday()
        dataMap["shipping_address[line_2]"] = address2.get()?.toReqBoday()
        dataMap["shipping_address[state]"] = state.get()?.toReqBoday()
        dataMap["shipping_address[city]"] = city.get()?.toReqBoday()
        dataMap["shipping_address[pincode]"] = pincode.get()?.toReqBoday()
        dataMap["subscription_identifier"] = App.INSTANCE.getUserType().toReqBoday()
        subscribeToSingle(ApiClient.getHeaderClient().create(WebserviceBuilder::class.java).updateProfile(
                dataMap,
                profile?.toMultipartBody("self_picture"),
                idProof?.toMultipartBody("id_proof"),
                shopPic?.toMultipartBody("shop_picture")
        )
                , object : SingleCallback<ApiResponse> {

            override fun onSingleSuccess(o: ApiResponse) {
                apiResponse.value = o
                dismissProgress()
            }

            override fun onFailure(throwable: Throwable) {
                dismissProgress()
                throwable.postError()
            }
        })
        return apiResponse
    }*/
}