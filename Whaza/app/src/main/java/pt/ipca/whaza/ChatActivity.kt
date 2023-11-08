package pt.ipca.whaza

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import pt.ipca.whaza.models.Chat

class ChatActivity : AppCompatActivity() {
    lateinit var lists: MutableList<Chat>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val db = Firebase.firestore

        db.collection("chats")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    lists.add(Chat(document.id,
                        document.data["usersids"],
                        document.data["date"],
                        document.data["name"].toString()))

                    //val test = Chat(document.data)


                    Toast.makeText(this, document.data["name"].toString(), Toast.LENGTH_SHORT).show()

                    //Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}