package org.supportcompact.networking

import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private val OKHTTP_TIMEOUT = 30 // seconds
    private lateinit var retrofit: Retrofit
    private lateinit var retrofitHeader: Retrofit
    private lateinit var okHttpClient: OkHttpClient
    const val BUILD_TYPE_DEBUG = true
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/" /// Latest url

    /**
     * @return [Retrofit] object its single-tone
     */
    fun getApiClient(): Retrofit {
        if (!::retrofit.isInitialized) {
            val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create()

            retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getOKHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
        return retrofit
    }

    /**
     * You can create multiple methods for different BaseURL
     *
     * @return [Retrofit] object
     */
    fun getApiClient(baseUrl: String): Retrofit {
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOKHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    /**
     * settings like caching, Request Timeout, Logging can be configured here.
     *
     * @return [OkHttpClient]
     */
    private fun getOKHttpClient(): OkHttpClient {
        return if (!::okHttpClient.isInitialized) {
            val builder = OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)

            if (BUILD_TYPE_DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                builder.addInterceptor(loggingInterceptor)
            }

            builder.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Accept", "application/json")
                chain.proceed(requestBuilder.build())
            }
            okHttpClient = builder.build()
            okHttpClient
        } else {
            okHttpClient
        }
    }


    fun getHeaderClient(header: String): Retrofit {

        if (::retrofitHeader.isInitialized)
            return retrofitHeader

        val builder = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor(header))
                .retryOnConnectionFailure(true)
                .connectTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(OKHTTP_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (BUILD_TYPE_DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        val okHttpClient = builder.build()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

        retrofitHeader = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofitHeader
    }

    private class HeaderInterceptor internal constructor(private val headerString: String) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            Log.v("Service", "Request")

            val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("token", headerString)
                    .build()

            return chain.proceed(request)
        }
    }
}

interface SingleCallback<A> {
    /**
     * @param o        Whole response Object
     * @param apiNames [A] to differentiate Apis
     */
    fun onSingleSuccess(o: Any?, apiNames: A)

    /**
     * @param throwable returns [Throwable] for checking Exception
     * @param apiNames  [A] to differentiate Apis
     */
    fun onFailure(throwable: Throwable, apiNames: A)
}

fun <T, A> subscribeToSingle(observable: Observable<T>, apiNames: A, singleCallback: SingleCallback<A>?) {
    Single.fromObservable(observable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<T> {
                override fun onSuccess(t: T) {
                    singleCallback?.onSingleSuccess(t, apiNames)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    singleCallback?.onFailure(e, apiNames)
                }
            })
}


interface SingleCallback1<T> {
    /**
     * @param o        Whole response Object
     * @param apiNames [A] to differentiate Apis
     */
    fun onSingleSuccess(o: T)

    /**
     * @param throwable returns [Throwable] for checking Exception
     * @param apiNames  [A] to differentiate Apis
     */
    fun onFailure(throwable: Throwable)
}

fun <T> subscribeToSingle(observable: Observable<T>, singleCallback: SingleCallback1<T>?) {
    Single.fromObservable(observable)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : SingleObserver<T> {
                override fun onSuccess(t: T) {
                    try {
                        singleCallback?.onSingleSuccess(t)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    singleCallback?.onFailure(e)
                }
            })
}