package pt.ipca.whaza.models

import android.os.Bundle
import com.google.firebase.ktx.Firebase
import pt.ipca.whaza.R

class User(id: String, name: String, email: String, groupid: Int) {

    val id: String
    val name: String
    val email: String
    //val password: String
    val groupid: Int

    init {
        this.id = id
        this.name = name
        this.email = email
        //this.password = password
        this.groupid = groupid
    }




}