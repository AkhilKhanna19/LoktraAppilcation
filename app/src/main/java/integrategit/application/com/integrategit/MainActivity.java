package integrategit.application.com.integrategit;

import android.os.AsyncTask;
import android.renderscript.Element;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private String url = "https://api.github.com/repos/arunagw/rails/commits";
    private ListView listView;
    CustomViewAdapter customViewAdapter;
    ArrayList<HashMap<String, String>> authorCommit = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getJsondata(url);
    }

    private void getJsondata(String url) {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("commit messages",response.toString());
                for(int i = 0;i<= response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        JSONObject commit = object.getJSONObject("commit");
                        JSONObject author = commit.getJSONObject("author");
                        HashMap<String,String> comm = new HashMap<>();
                        String name = author.getString("name");
                        String email = author.getString("email");
                        String message = commit.getString("message");

                        comm.put("name",name);
                        comm.put("email", email);
                        comm.put("commit",message);
                        authorCommit.add(comm);

                        listView = (ListView) findViewById(R.id.list_item_id);


                        customViewAdapter = new CustomViewAdapter(getApplicationContext(),authorCommit);
                        listView.setAdapter(customViewAdapter);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);

    }
}