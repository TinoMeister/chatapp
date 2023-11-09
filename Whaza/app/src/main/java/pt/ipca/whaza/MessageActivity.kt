package pt.ipca.whaza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import pt.ipca.whaza.customs.CustomChatListView
import pt.ipca.whaza.customs.CustomMessageListView
import pt.ipca.whaza.models.Chat
import pt.ipca.whaza.models.Message
import pt.ipca.whaza.models.User
import java.time.LocalDate
import java.util.Date

class MessageActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val messages = mutableListOf<Message>()
    val lst: ListView by lazy {
        findViewById<ListView>(R.id.message_lst)
    }
    val text: TextView by lazy{
        findViewById<TextView>(R.id.message_tv)
    }
    val btn: ImageButton by lazy{
        findViewById<ImageButton>(R.id.message_btn)
    }
    val users = mutableListOf<User>()
    lateinit var chatId: String
    lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        chatId = intent.getStringExtra("chatId")!!
        userId = intent.getStringExtra("userId")!!

        btn.setOnClickListener{
            sendMessage()
        }

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

    fun sendMessage(){
        val body = text.text.toString()
        val id = db.collection("messages").document().id
        val myMessage = Message(
            id = id,
            chatid = chatId,
            userid = userId,
            date = Date(),
            body = body,
            messagetypeid = 1
        )
        db.collection("messages")
            .document(id)
            .set(myMessage)
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
}