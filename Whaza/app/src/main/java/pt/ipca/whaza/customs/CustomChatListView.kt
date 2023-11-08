package pt.ipca.whaza.customs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.ipca.whaza.R
import pt.ipca.whaza.models.Chat

class CustomChatListView(context: Context, resource: Int, chats: MutableList<Chat>) :
    ArrayAdapter<Chat>(context, resource, chats)  {

    private var mContext: Context
    private var mValues: MutableList<Chat>
    private var mResource: Int

    init {
        mContext = context
        mValues = chats
        mResource = resource
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View

        if (convertView != null) {
            view = convertView
        }
        else {
            view = LayoutInflater.from(mContext).inflate(mResource, parent, false)
            view.tag = MyViewHolder(view)
        }

        val vh: MyViewHolder = view.tag as MyViewHolder
        val value = mValues[position]

        vh.name?.text = value.name
        vh.time?.text = value.date.toString()

        return view
    }

    private class MyViewHolder(view: View?) {
        val name = view?.findViewById<TextView>(R.id.custom_chat_title_iv)
        val time = view?.findViewById<TextView>(R.id.custom_chat_time_iv)
    }
}