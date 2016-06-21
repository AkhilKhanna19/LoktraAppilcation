package integrategit.application.com.integrategit;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import Model.*;

/**
 * Created by akhil on 16/6/16.
 */
public class detailViewHolder extends RecyclerView.ViewHolder {
    private final TextView name, message,email;
    private SimpleDraweeView simpleDraweeView;

    public detailViewHolder(View itemView) {
        super(itemView);
        email = (TextView) itemView.findViewById(R.id.email_id);
        name = (TextView) itemView.findViewById(R.id.author_name);
        message =(TextView) itemView.findViewById(R.id.commit_message);
        simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.image_id);
    }
    public void bind(details model) {
        Uri uri = Uri.parse(model.getImageUrl());
        simpleDraweeView.setImageURI(uri);
        email.setText("Email id:" +model.getEmail());
        name.setText(model.getName());
        message.setText("Commit message:" +model.getMessage());
    }
}
