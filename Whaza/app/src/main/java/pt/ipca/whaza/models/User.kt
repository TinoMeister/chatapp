package pt.ipca.whaza.models

import android.os.Bundle
import com.google.firebase.ktx.Firebase
import pt.ipca.whaza.R

data class User(var id: String? = "",
                var name: String? = "",
                var email: String? = "",
                var groupid: Int? = 0)