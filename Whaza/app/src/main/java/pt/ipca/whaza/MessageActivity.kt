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
import pt.ipca.whaza.models.User

class MessageActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val messages = mutableListOf<Message>()
    val lst = findViewById<ListView>(R.id.message_lst)
    val users = mutableListOf<User>()
    lateinit var chatId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        val userId = intent.getStringExtra("userId")
        chatId = intent.getStringExtra("chatId")!!

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val myUser = document.toObject(User::class.java)
                    users.add(myUser)
                }
                getMessages()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getMessages() {
        db.collection("messages")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val myMessage = document.toObject(Message::class.java)
                    for (user in users) {
                        if(user.id == myMessage.userid)
                            myMessage.user = user
                    }

                    if (myMessage.chatid == chatId)
                        messages.add(myMessage)
                }

                val adapter = CustomMessageListView(baseContext, R.layout.custommessagelistview ,messages)
                lst.adapter = adapter
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}