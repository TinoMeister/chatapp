package pt.ipca.whaza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val db = Firebase.firestore

        db.collection("chats")
            .get()
            .addOnSuccessListener { result ->
                Toast.makeText(this, result.toString(), Toast.LENGTH_SHORT).show()
                for (document in result) {

                    //Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}