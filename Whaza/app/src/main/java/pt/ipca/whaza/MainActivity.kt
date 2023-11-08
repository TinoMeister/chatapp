package pt.ipca.whaza

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.util.Pair
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val email: TextInputLayout by lazy {
        findViewById(R.id.login_email_til)
    }
    val password: TextInputLayout by lazy {
        findViewById(R.id.login_password_til)
    }
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<TextView>(R.id.login_login_btn)
        btn.setOnClickListener { doLogin(btn) }
        auth = Firebase.auth

        val bounds = ChangeBounds().setDuration(1000)
        window.sharedElementEnterTransition = bounds

        val name = findViewById<TextView>(R.id.login_welcome_tv)
        val desc = findViewById<TextView>(R.id.login_desc_tv)

        val btnSignUp = findViewById<Button>(R.id.login_register_btn)
        btnSignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)

            val options = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity,
                Pair.create(name, "name_tn"),
                Pair.create(desc, "desc_tn"),
                Pair.create(email, "email_tn"),
                Pair.create(password, "password_tn"),
                Pair.create(btn, "signin_tn"),
                Pair.create(btnSignUp, "signin_signup_tn"))

            startActivity(intent, options.toBundle())
        }
    }

    fun doLogin(v: View) {
        val email = email.editText!!.text.toString()
        val password = password.editText!!.text.toString()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(
                        baseContext,
                        "Authentication success",
                        Toast.LENGTH_SHORT,
                    ).show()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }


}
