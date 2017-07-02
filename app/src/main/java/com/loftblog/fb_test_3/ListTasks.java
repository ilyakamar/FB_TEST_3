package com.loftblog.fb_test_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListTasks extends AppCompatActivity {// Start ListTasks


    public FirebaseAuth mAuth;
    private DatabaseReference myRef;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    FirebaseListAdapter mAdapter;

    private Button btn_new_task; // ET_new_task
    private EditText et_new_task;// Btn_new_task

   // ListView listViewUserTasks; // ListUserTasks

    private static class TaskViewHolder extends RecyclerView.ViewHolder{

        TextView mTitleTask;
        Button mDel;

        public TaskViewHolder(View itemView) {
            super(itemView);

            mTitleTask = (TextView)itemView.findViewById(R.id.tv_title_task);
            mDel = (Button)itemView.findViewById(R.id.btn_del);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {// Start onCreate
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);

     //   listViewUserTasks = (ListView) findViewById(R.id.discr_for_task);

        myRef = FirebaseDatabase.getInstance().getReference(); // initsialeziruem nashu silku na bazu danih

        // palu4im silku na defoltnuiu bazu danih
      /*  mAdapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, myRef.child(user.getUid()).child("Tasks")) {// peredav v ka4estve parametrav activiti class skatorim adapter budet rabotat
            @Override
            protected void populateView(View v, String s, int position) {// polu4aet na vhod object view nashu straku i pozitsiu dlya izmeneniya faila maketa >>>>>>>>>> metod budit vizivatsa kazdiy raz kakda danie budut izmeneny
                TextView text = (TextView) v.findViewById(android.R.id.text1);
                text.setText(s);
            }
        };
        // ustanovim nash adapter
        listViewUserTasks.setAdapter(mAdapter);

        // pereidyom k dabavleneu danih >> naydyom nashi polya dlya voda i knopku dabavit
        btn_new_task = (Button) findViewById(R.id.btn_add);

        et_new_task = (EditText) findViewById(R.id.et_new_tasks);

        btn_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(user.getUid()).child("Tasks").push().setValue(et_new_task.getText().toString());
            }
        });
    */

      // dabavleniya danih v firebase database
        btn_new_task = (Button) findViewById(R.id.btn_add);
        et_new_task = (EditText) findViewById(R.id.et_new_tasks);



        btn_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(user.getUid()).
                        child("Tasks").
                        push().
                        setValue(et_new_task.getText().toString());
            }
        });

       RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list_tasks) ;

        // budim peredavat danie4
        FirebaseRecyclerAdapter<String,TaskViewHolder> adapter;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new FirebaseRecyclerAdapter<String, TaskViewHolder>(String.class,R.layout.task_layout,TaskViewHolder.class,myRef.child(user.getUid()).child("Tasks")) {

            @Override
            protected void populateViewHolder(TaskViewHolder viewHolder, String title,final int position) {
                viewHolder.mTitleTask.setText(title);
                viewHolder.mDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                            DatabaseReference itemRef = getRef(position);
                            itemRef.removeValue();


                    }
                });
            }
        };

        recyclerView.setAdapter(adapter);

    }//end onCreate


}//END  ListTasks
