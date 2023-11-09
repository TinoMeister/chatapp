package pt.ipca.whaza

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import pt.ipca.whaza.models.Chat
import pt.ipca.whaza.models.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    val name: TextInputLayout by lazy {
        findViewById(R.id.register_name_til)
    }
    val email: TextInputLayout by lazy {
        findViewById(R.id.register_email_til)
    }
    val password: TextInputLayout by lazy {
        findViewById(R.id.register_password_til)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = Firebase.auth
        db = Firebase.firestore

        val btn = findViewById<Button>(R.id.register_register_btn)
        btn.setOnClickListener { doRegister(btn) }

        val btnLogin = findViewById<Button>(R.id.register_login_btn)
        btnLogin.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun doRegister(v: View) {
        val name = name.editText!!.text.toString()
        val email = email.editText!!.text.toString()
        val password = password.editText!!.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val newUser = User(user!!.uid, name, email, 4)
                    addUsers(newUser)
                    updateChat(user.uid)
                } else {
                    Toast.makeText(
                        baseContext,
                        "Register failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    fun addUsers(user: User) {
        db.collection("users")
            .document(user.id)
            .set(user)
            .addOnSuccessListener { _ ->
                Toast.makeText(
                    baseContext,
                    "Register success",
                    Toast.LENGTH_SHORT,
                ).show()
            }
            .addOnFailureListener { _ ->
                Toast.makeText(
                    baseContext,
                    "Register failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }

    fun updateChat(id: String) {
        db.collection("chats")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val myChat = document.toObject(Chat::class.java)
                    myChat.userids.add(id)

                    db.collection("chats")
                        .document(document.id)
                        .set(myChat)
                        .addOnSuccessListener { _ ->
                        }
                        .addOnFailureListener { _ ->
                        }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    baseContext,
                    "Error.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
}