package integrategit.application.com.integrategit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private EditText repo_name, repo;
    private Button default_but, sub_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        repo_name = (EditText) findViewById(R.id.user_name);
        repo = (EditText) findViewById(R.id.repo_id);
        default_but = (Button) findViewById(R.id.default_id);
        sub_but = (Button) findViewById(R.id.submit_id);



        default_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                    intent.putExtra("name", "rails");
                    intent.putExtra("repo", "rails");
                    startActivity(intent);


            }
        });

        sub_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repo_name.getText().toString().isEmpty() && repo.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Enter Something or select default", Toast.LENGTH_LONG).show();
                }
                else{

                Intent intent = new Intent(Main3Activity.this, MainActivity.class);
                intent.putExtra("name",repo_name.getText().toString().trim());
                intent.putExtra("repo", repo.getText().toString().trim());
                startActivity(intent);}
            }
        });








    }
}
