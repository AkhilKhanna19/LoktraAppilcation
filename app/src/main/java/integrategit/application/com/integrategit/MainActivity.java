package integrategit.application.com.integrategit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import Model.details;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private String url = "https://api.github.com/repos/";
    private RecyclerView recyclerview;
    private details detail;
    private ProgressDialog progressDialog;
    CustomViewAdapter customViewAdapter;


    private String name, email, message, repoNam, repo, avatar;
    private ArrayList<details> detailsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        Bundle extras = getIntent().getExtras();
        repoNam = extras.getString("name").toString();
        repo = extras.getString("repo").toString();
        getJsondata(url + repoNam + "/" + repo + "/commits");

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_bar, menu);
        final MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        searchView.setOnQueryTextListener(this);
        return true;
    }


    private void getJsondata(String url) {
        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("commit messages", response.toString());
                for (int i = 0; i <= response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        JSONObject commit = object.getJSONObject("commit");
                        JSONObject auth = object.getJSONObject("author");
                        avatar = auth.getString("avatar_url");

                        JSONObject author = commit.getJSONObject("author");


                        detail = new details();
                        name = author.getString("name");
                        email = author.getString("email");
                        message = commit.getString("message");

                        detailsArrayList.add(i, new details(name, message, email, avatar));


                        recyclerview = (RecyclerView) findViewById(R.id.recyclerView);
                        recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                        customViewAdapter = new CustomViewAdapter(getApplicationContext(), detailsArrayList);
                        progressDialog.dismiss();
                        recyclerview.setAdapter(customViewAdapter);


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


    @Override
    public boolean onQueryTextSubmit(String query) {
        getJsondata(url + repoNam + "/" + repo + "/commits");

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<details> filteredModelList = filter(detailsArrayList, newText);
        customViewAdapter.animateTo(filteredModelList);
        recyclerview.scrollToPosition(0);


        return true;
    }

    private ArrayList<details> filter(ArrayList<details> authorCommit, String newText) {
        newText = newText.toLowerCase();
        final ArrayList<details> filteredModelList = new ArrayList<>();
        for (details model : authorCommit) {
            String name = model.getName().toLowerCase();
            String message = model.getMessage().toLowerCase();
            if (name.contains(newText) || message.contains(newText)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

}




