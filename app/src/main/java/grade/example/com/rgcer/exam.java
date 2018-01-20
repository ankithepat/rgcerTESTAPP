package grade.example.com.rgcer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class exam extends AppCompatActivity {

    private DatabaseReference mDatabase,ques2,ques3,ques4,ques5;

    private TextView question,question2,question3,question4,question5;
    private Button btn1,btn2,btn3,btn4,btn5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Questions").child("math");


        question2= (TextView) findViewById(R.id.question2);
        btn1 = (Button) findViewById(R.id.opt1);

        btn2 = (Button) findViewById(R.id.opt2);

        btn3 = (Button) findViewById(R.id.opt3);

        btn4 = (Button) findViewById(R.id.opt4);


        btn5 = (Button) findViewById(R.id.fab);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {







                Iterable<DataSnapshot> ds = dataSnapshot.getChildren();
                Iterator<DataSnapshot> ids = ds.iterator();
                //getting maximum number of children
                long allNum = dataSnapshot.getChildrenCount();
                int maxNum = (int)allNum;

                //getting the random integer from number of children
                int randomNum = new Random().nextInt(maxNum);

                int count = 0;

                //has next will check the next value while count is used as a position substitute.
                while(ids.hasNext() && count < randomNum) {
                    ids.next();
                    count ++; // used as positioning.
                }
                Map<String, Object> newPost = (Map<String, Object>) ids.next().getValue();
                String msg = newPost.get("question").toString();
                question2.setText("Q1:"+ msg);
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(this, exam);
                        startActivity(myIntent)

                    }
                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



     /*  ques2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ques = dataSnapshot.getValue().toString();
                btn1.setText("Q1:"+ ques);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ques3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ques = dataSnapshot.getValue().toString();
                question3.setText("Q3:"+ ques);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ques4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ques = dataSnapshot.getValue().toString();
                question4.setText("Q4:"+ ques);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ques5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String ques = dataSnapshot.getValue().toString();
                question5.setText("Q5:"+ ques);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
