package com.fawzi.firestore.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fawzi.firestore.databinding.ActivityMainBinding;
import com.fawzi.firestore.models.Contact;
import com.fawzi.firestore.ui.add_contact.AddContactDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.MainListener {

    private ActivityMainBinding binding;
    private List<Contact> data = new ArrayList<>();
    private ContactAdapter adapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new MainPresenter(this, this);

        presenter.loadContacts();

        setContacts();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AddContactDetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setContacts() {
        adapter = new ContactAdapter(this, data);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        binding.rv.setHasFixedSize(true);
        binding.rv.setAdapter(adapter);
    }

    @Override
    public void getContacts(List<Contact> contacts) {
        this.data.addAll(contacts);
        binding.progressBar.setVisibility(View.GONE);
        binding.rv.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onRestart() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.rv.setVisibility(View.GONE);
        this.data.clear();
        presenter.loadContacts();
        super.onRestart();
    }

}