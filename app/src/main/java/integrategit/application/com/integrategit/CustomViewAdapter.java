package integrategit.application.com.integrategit;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Model.details;

/**
 * Created by akhil on 3/6/16.
 */
class CustomViewAdapter extends RecyclerView.Adapter<detailViewHolder> {
    private Context mContext;
    private ArrayList<details> messages;
    //private ArrayList<HashMap<String, String>> mess;
    private static LayoutInflater inflater = null;
    private SimpleDraweeView simpleDraweeView;



    public CustomViewAdapter(Context context, ArrayList<details> data) {

        mContext = context;
        messages = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


   /* @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }*/

    @Override
    public detailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.row_design, parent, false);
        return new detailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(detailViewHolder holder, int position) {
        final details model = messages.get(position);
        holder.bind(model);

    }





    @Override
    public int getItemCount() {
        return messages.size();
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (convertView == null) {

            view = inflater.inflate(R.layout.row_design, null);
            TextView name = (TextView) view.findViewById(R.id.author_name);
            TextView emailid = (TextView) view.findViewById(R.id.email_id);
            TextView commit = (TextView) view.findViewById(R.id.commit_message);
            //HashMap<String, details> mMessage = new HashMap<>();


            //mMessage = messages.get(position);


            name.setText(messages.get(position).getName());
            emailid.setText("Email id:" + messages.get(position).getEmail());
            commit.setText("Commit message:" + messages.get(position).getMessage());


            Uri uri = Uri.parse(messages.get(position).getImageUrl());
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_id);
            simpleDraweeView.setImageURI(uri);


        }
        return view;
    }*/

    public void animateTo(ArrayList<details> mess) {
        applyAndAnimateRemovals(mess);
        applyAndAnimateAdditions(mess);
        applyAndAnimateMovedItems(mess);
    }

    private void applyAndAnimateRemovals(ArrayList<details> mess) {
        for (int i = messages.size() - 1; i >= 0; i--) {
            details detail = messages.get(i);
            if (!mess.contains(detail)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(ArrayList<details> mess) {
        for (int i = 0, count = mess.size(); i< count; i++) {
            details model = mess.get(i);
            if (!messages.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(ArrayList<details> mess) {
        for (int toPosition = mess.size() - 1; toPosition >= 0; toPosition--) {
            details model = mess.get(toPosition);
            int fromPosition = messages.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    public details removeItem(int position) {
        details model = messages.remove(position);
        notifyItemRemoved(position);

        return model;
    }

    public void addItem(int position, details model) {
        messages.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        details model = messages.remove(fromPosition);
        messages.add(toPosition, model);
        notifyItemMoved(fromPosition,toPosition);

    }
}




