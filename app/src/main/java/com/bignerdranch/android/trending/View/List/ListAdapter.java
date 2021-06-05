package com.bignerdranch.android.trending.View.List;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bignerdranch.android.trending.Model.User;
import com.bignerdranch.android.trending.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

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

        private SimpleDraweeView headimage;
        private TextView username;
        private TextView reponame;
        private TextView desc;
        private TextView lang;
        private TextView stars;
        private TextView forks;
        private ConstraintLayout mConstraintLayout;
        private ConstraintLayout mLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            headimage = itemView.findViewById(R.id.headimage);
            username = itemView.findViewById(R.id.Username);
            reponame = itemView.findViewById(R.id.Reponame);
            desc = itemView.findViewById(R.id.desc);
            lang = itemView.findViewById(R.id.lang);
            stars = itemView.findViewById(R.id.stars);
            forks = itemView.findViewById(R.id.forks);
            mConstraintLayout = itemView.findViewById(R.id.hidden_list);
            mLayout = itemView.findViewById(R.id.list_item);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        String [] repo = UserList.get(position).getRepo().split("/");

        List<String> ava = UserList.get(position).getAvatars();
        if (ava != null && ava.size()>0){
            Uri uri =  Uri.parse(ava.get(0));
            holder.headimage.setImageURI(uri);
        }
        holder.username.setText(repo[0]);
        holder.reponame.setText(repo[1]);
        holder.desc.setText(UserList.get(position).getDesc());
        holder.lang.setText(UserList.get(position).getLang());
        holder.stars.setText(UserList.get(position).getStars());
        holder.forks.setText(UserList.get(position).getForks());
        holder.mConstraintLayout.setVisibility(View.GONE);
        holder.mLayout.setOnClickListener(new View.OnClickListener() {

            boolean flag = false;

            @Override
            public void onClick(View v) {

                if (!flag){
                    holder.mConstraintLayout.setVisibility(View.GONE);
                    flag = true;
                }else {
                    holder.mConstraintLayout.setVisibility(View.VISIBLE);
                    flag = false;
                }
            }
        });

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
