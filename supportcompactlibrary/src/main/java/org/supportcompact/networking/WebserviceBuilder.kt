package org.supportcompact.networking

import io.reactivex.Observable


/**
 * Declare all the APIs in this class with specific interface
 * e.g. Profile for Login/Register Apis
 */
interface WebserviceBuilder {

    fun getData(): Observable<String>

    /**
     * ApiNames to differentiate APIs
     */
    enum class ApiNames {
        getData
    }

}