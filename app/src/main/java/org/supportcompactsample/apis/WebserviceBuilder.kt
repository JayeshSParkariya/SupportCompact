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

    /*@GET("participants/{participant_id}")
    fun getUserProfilePic(
            @Path("participant_id") participant_id: String
    ): Observable<UserProfilePicBaseResponse>*/

    /*@Multipart
    @PUT("participants/my_profile_update")
    fun updateProfile(
            @PartMap data: HashMap<String, RequestBody?>,
            @Part profilePic: MultipartBody.Part?,
            @Part idProof: MultipartBody.Part?,
            @Part shopPic: MultipartBody.Part?
    ): Observable<ApiResponse>*/

    /*@FormUrlEncoded
    @POST("device_infos")
    fun sendDeviceInfo(@FieldMap reqParams: HashMap<String, String>): Observable<ApiResponse>*/

    /*@FormUrlEncoded
    @POST("otp/verify_otp_in_valid_time_against_participant")
    fun verifyOtp(
            @Field("mobile_number") mobile_number: String,
            @Field("otp") otp: String
    ): Observable<ApiResponse>*/

    /*@POST("orders/self_order")
    fun placeOrder(@Body data: JsonObject): Observable<ApiResponse>*/

    /**
     * ApiNames to differentiate APIs
     */
    enum class ApiNames {
        GetTodoList
    }
}