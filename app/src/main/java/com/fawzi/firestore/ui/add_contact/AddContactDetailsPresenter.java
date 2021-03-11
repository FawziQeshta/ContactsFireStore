package com.fawzi.firestore.ui.add_contact;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.fawzi.firestore.models.Contact;
import com.fawzi.firestore.utils.Constants;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddContactDetailsPresenter {

    private Context context;
    private PresenterListener listener;
    private FirebaseFirestore db;
    private ProgressDialog progressDialog;

    public AddContactDetailsPresenter(Context context, PresenterListener listener) {
        this.listener = listener;
        this.context = context;
        db = FirebaseFirestore.getInstance();
        dialogInit();
    }

    private void dialogInit() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please Wait...");
    }

    public void saveContactDetails(Contact contact) {

        progressDialog.show();

        Map<String, Object> map = new HashMap<>();
        map.put(Constants.USER_NAME, contact.getName());
        map.put(Constants.USER_EMAIL, contact.getEmail());
        map.put(Constants.USER_ADDRESS, contact.getAddress());
        map.put(Constants.USER_NUMBER, contact.getNumber());

        // Add a new document with a generated ID
        db.collection(Constants.COLLECTION_NAME)
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "The data has been saved successfully", Toast.LENGTH_SHORT).show();
                        listener.finishedOperation();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(context, "An error occurred while saving the data", Toast.LENGTH_SHORT).show();
                        listener.finishedOperation();
                    }
                });

    }

    public interface PresenterListener {
        void finishedOperation();
    }

}
