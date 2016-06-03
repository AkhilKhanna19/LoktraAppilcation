package integrategit.application.com.integrategit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView Aname, emailid, mess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Aname = (TextView) findViewById(R.id.text_name);
        emailid =(TextView) findViewById(R.id.email_id_d);
        mess = (TextView) findViewById(R.id.message_commit);
        Bundle bundle = getIntent().getExtras();


        Aname.setText(bundle.getString("name"));
        emailid.setText(bundle.getString("email"));
        mess.setText(bundle.getString("message"));




    }
}
