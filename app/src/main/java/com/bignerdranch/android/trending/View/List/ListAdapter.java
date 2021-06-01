package com.bignerdranch.android.trending.View.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.R;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<User> UserList;

    public ListAdapter(List<User> userList){
        this.UserList = userList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView headimage;
        private TextView username;
        private TextView reponame;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headimage = itemView.findViewById(R.id.headimage);
            username = itemView.findViewById(R.id.Username);
            reponame = itemView.findViewById(R.id.Reponame);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {

        holder.headimage.setImageResource(UserList.get(position).getImgID());
        holder.username.setText(UserList.get(position).getUsername());
        holder.reponame.setText(UserList.get(position).getReponame());

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    public void replaceUser(List<User> userlist){
        if (userlist != null){
            int t = UserList.size();
            UserList.clear();
            notifyItemRangeRemoved(0, t);
            UserList.addAll(userlist);
            notifyItemRangeInserted(0, userlist.size());
        }
    }
}
