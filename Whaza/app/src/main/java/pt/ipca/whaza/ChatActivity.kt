package pt.ipca.whaza

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import pt.ipca.whaza.customs.CustomChatListView
import pt.ipca.whaza.models.Chat

class ChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        val userId = intent.getStringExtra("userId")
        val db = Firebase.firestore
        val lists = mutableListOf<Chat>()
        val lst = findViewById<ListView>(R.id.chat_lst)

        db.collection("chats")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val myChat = document.toObject(Chat::class.java)
                    if (myChat.userids.contains(userId))
                        lists.add(myChat)
                }

                val adapter = CustomChatListView(baseContext, R.layout.customchatlistview ,lists)
                lst.adapter = adapter

                lst.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    val chat = (adapterView.adapter as CustomChatListView).getItem(position)

                    val intent = Intent(this@ChatActivity, MessageActivity::class.java)
                    intent.putExtra("chatId", chat!!.id)
                    intent.putExtra("userId", userId)
                    startActivity(intent)
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }
}