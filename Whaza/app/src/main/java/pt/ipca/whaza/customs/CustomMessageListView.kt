package pt.ipca.whaza.customs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import pt.ipca.whaza.R
import pt.ipca.whaza.models.Message

class CustomMessageListView(context: Context, resource: Int, messages: MutableList<Message>) :
    ArrayAdapter<Message>(context, resource, messages)  {

    private var mContext: Context
    private var mValues: MutableList<Message>
    private var mResource: Int

    init {
        mContext = context
        mValues = messages
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

        vh.body?.text = value.body
        vh.time?.text = value.date.toString()

        return view
    }

    private class MyViewHolder(view: View?) {
        val name = view?.findViewById<TextView>(R.id.custom_message_name_tv)
        val body = view?.findViewById<TextView>(R.id.custom_message_body_tv)
        val time = view?.findViewById<TextView>(R.id.custom_message_time_tv)
    }
}