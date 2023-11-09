package pt.ipca.whaza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import pt.ipca.whaza.customs.CustomChatListView
import pt.ipca.whaza.customs.CustomMessageListView
import pt.ipca.whaza.models.Chat
import pt.ipca.whaza.models.Message

class MessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val userId = intent.getStringExtra("userId")
        val chatId = intent.getStringExtra("chatId")
        val db = Firebase.firestore

        val lists = mutableListOf<Message>()
        val lst = findViewById<ListView>(R.id.message_lst)

        db.collection("messages")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val myMessage = document.toObject(Message::class.java)
                    if (myMessage.chatid == chatId)
                        lists.add(myMessage)
                }

                val adapter = CustomMessageListView(baseContext, R.layout.custommessagelistview ,lists)
                lst.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}