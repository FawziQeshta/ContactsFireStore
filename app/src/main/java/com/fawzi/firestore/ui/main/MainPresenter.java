package com.fawzi.firestore.ui.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fawzi.firestore.models.Contact;
import com.fawzi.firestore.ui.add_contact.AddContactDetailsPresenter;
import com.fawzi.firestore.utils.Constants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter {

    private Context context;
    private MainListener listener;
    private FirebaseFirestore db;

    public MainPresenter(Context context, MainListener listener) {
        this.listener = listener;
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }

    public void loadContacts() {

        db.collection(Constants.COLLECTION_NAME)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Contact> contactList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Contact contact = new Contact();
                                contact.setId(document.getId());
                                contact.setName(String.valueOf(document.getData().get(Constants.USER_NAME)));
                                contact.setEmail(String.valueOf(document.getData().get(Constants.USER_EMAIL)));
                                contact.setAddress(String.valueOf(document.getData().get(Constants.USER_ADDRESS)));
                                contact.setNumber(String.valueOf(document.getData().get(Constants.USER_NUMBER)));

                                contactList.add(contact);
                            }

                            listener.getContacts(contactList);

                        } else {
                            Toast.makeText(context, "An error occurred while reading the data", Toast.LENGTH_SHORT).show();
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    public interface MainListener {
        void getContacts(List<Contact> contacts);
    }

}
