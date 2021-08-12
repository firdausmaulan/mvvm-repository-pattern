package com.mvvm.mindorks.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mvvm.mindorks.data.model.presentation.User
import com.mvvm.mindorks.databinding.UserItemBinding

class UserAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var listener : Listener? = null

    interface Listener{
        fun onClick(user: User)
    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            Glide.with(ivAvatar.context)
                .load(users[position].avatarUrl)
                .into(ivAvatar)
            tvUsername.text = users[position].login
            tvUrl.text = users[position].htmlUrl
            container.setOnClickListener { listener?.onClick(users[position]) }
        }
    }

    override fun getItemCount() = users.size

    fun addAll(list: List<User>) {
        for (user in list) {
            users.add(user)
            notifyItemInserted(users.size - 1)
        }
    }

    fun clear(){
        users.clear()
        notifyDataSetChanged()
    }
}