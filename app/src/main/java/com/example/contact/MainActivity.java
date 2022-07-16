package com.example.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemEventListener {
    private ContactsAdapter adapter;
    private int editingItemPosition=-1;
    private EditText fullNameEt;
    private ImageView addNewContactBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView=findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        adapter=new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);

       fullNameEt=findViewById(R.id.et_main_contactFullname);
        addNewContactBtn=findViewById(R.id.iv_main_addNewContact);
        addNewContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fullNameEt.length()>0){
                    if (editingItemPosition>-1){
                        adapter.updateContact(fullNameEt.getText().toString(),editingItemPosition);
                        editingItemPosition=-1;
                        addNewContactBtn.setImageResource(R.drawable.ic_baseline_add_24);

                    }else {
                        adapter.addNewContact(fullNameEt.getText().toString());
                        recyclerView.scrollToPosition(0);
                    }
                    fullNameEt.setText("");


                }
            }
        });

    }

    @Override
    public void onItemClick(String fullName, int position) {
        editingItemPosition=position;
        fullNameEt.setText(fullName);
        addNewContactBtn.setImageResource(R.drawable.ic_baseline_done_24);
    }
}