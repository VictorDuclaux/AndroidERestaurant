package fr.isen.duclaux.androiderestaurant

import com.google.gson.annotations.SerializedName

class JsonUser (
    //id_shop, firstname, lastname, address, email, password
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("address") val address: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)


