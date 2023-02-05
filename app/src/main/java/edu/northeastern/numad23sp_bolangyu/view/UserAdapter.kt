package edu.northeastern.numad23sp_bolangyu.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.northeastern.numad23sp_bolangyu.R
import edu.northeastern.numad23sp_bolangyu.model.Userdata


class UserAdapter(val c:Context,val userList:ArrayList<Userdata>):RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val v: View):RecyclerView.ViewHolder(v){
        val name = v.findViewById<TextView>(R.id.Name)
        val url = v.findViewById<TextView>(R.id.URL)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.list,parent,false)
        return UserViewHolder(v)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val newList = userList[position]
        holder.name.text = newList.userName
        holder.url.text = newList.URL

    }
}