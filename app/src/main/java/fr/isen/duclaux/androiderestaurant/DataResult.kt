package fr.isen.duclaux.androiderestaurant


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class DataResult (@SerializedName("data") val data: List<Category>): Serializable


