package pt.ipca.whaza

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import pt.ipca.whaza.models.Chat
import pt.ipca.whaza.models.User
import java.text.DateFormat

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    val email: TextInputLayout by lazy {
        findViewById(R.id.login_email_til)
    }
    val password: TextInputLayout by lazy {
        findViewById(R.id.login_password_til)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth

        val btn = findViewById<Button>(R.id.login_login_btn)
        btn.setOnClickListener { doLogin(btn) }

        val btnSignUp = findViewById<Button>(R.id.login_register_btn)
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    fun doLogin(v: View) {
        var email = email.editText!!.text.toString()
        var password = password.editText!!.text.toString()

        email = "grupo4@gmail.com"
        password = "abcd1234"

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    Toast.makeText(
                        baseContext,
                        "Authentication success",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val intent = Intent(this@MainActivity, ChatActivity::class.java)
                    intent.putExtra("userId", user!!.uid)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }
}
