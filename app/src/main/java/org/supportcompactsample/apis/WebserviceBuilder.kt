package org.supportcompactsample.apis

import io.reactivex.Observable
import org.supportcompactsample.apis.models.Todo
import retrofit2.http.GET


/**
 * Declare all the APIs in this class with specific interface
 * e.g. Profile for Login/Register Apis
 */
interface WebserviceBuilder {

    @GET("todos")
    fun getTodos(): Observable<List<Todo>>

    /**
     * ApiNames to differentiate APIs
     */
    enum class ApiNames {
        GetTodoList
    }
}