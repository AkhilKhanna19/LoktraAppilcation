package integrategit.application.com.integrategit;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akhil on 3/6/16.
 */
class CustomViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<HashMap<String, String>> messages;
    private static LayoutInflater inflater = null;
    private SimpleDraweeView simpleDraweeView;


    public CustomViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {

        mContext = context;
        messages = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (convertView == null)
            view = inflater.inflate(R.layout.row_design, null);
            TextView name = (TextView) view.findViewById(R.id.author_name);
            TextView emailid = (TextView) view.findViewById(R.id.email_id);
            TextView commit = (TextView) view.findViewById(R.id.commit_message);
            HashMap<String, String> mMessage = new HashMap<>();

            mMessage = messages.get(position);



            name.setText(mMessage.get("name"));
            emailid.setText("Email id:" +mMessage.get("email"));
            commit.setText("Commit message:" +mMessage.get("commit"));


            Uri uri= Uri.parse(mMessage.get("image"));
            simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image_id);
            simpleDraweeView.setImageURI(uri);







        
        return view;
    }
}


