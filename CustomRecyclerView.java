package integrategit.application.com.integrategit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class CustomViewAdapter extends RecyclerView.Adapter<CustomViewAdapter.MyViewHolder> {

   private Context mContext;
    private ArrayList<HashMap<String, String>> messages;
    private static LayoutInflater inflater = null;


    public CustomViewAdapter(Context context, ArrayList<HashMap<String, String>> data) {

        mContext = context;
        messages = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            TextView name = (TextView) view.findViewById(R.id.author_name);
            TextView emailid = (TextView) view.findViewById(R.id.email_id);
            TextView commit = (TextView) view.findViewById(R.id.commit_message);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design, parent, false);

        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

            
            HashMap<String, String> mMessage = new HashMap<>();

            mMessage = messages.get(position);



            name.setText(mMessage.get("name"));
            emailid.setText("Email id:" +mMessage.get("email"));
            commit.setText("Commit message:" +mMessage.get("commit"));
     }



    @Override
    public int getItemCount() {
        return messages.size();
    }
}
