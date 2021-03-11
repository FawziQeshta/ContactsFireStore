package com.fawzi.firestore.ui.add_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fawzi.firestore.R;
import com.fawzi.firestore.databinding.ActivityAddContactDetailsBinding;
import com.fawzi.firestore.models.Contact;

import java.util.Objects;

public class AddContactDetailsActivity extends AppCompatActivity implements View.OnClickListener, AddContactDetailsPresenter.PresenterListener {

    private ActivityAddContactDetailsBinding binding;
    private AddContactDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new AddContactDetailsPresenter(this, this);

        binding.buttonSave.setOnClickListener(this);

        binding.toolbar.setNavigationIcon(R.drawable.ic_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private boolean checkUserInputs() {
        if (!Objects.requireNonNull(binding.editTextName.getText()).toString().isEmpty()
                && !Objects.requireNonNull(binding.editTextEmail.getText()).toString().isEmpty()
                && !Objects.requireNonNull(binding.editTextAddress.getText()).toString().isEmpty()
                && !Objects.requireNonNull(binding.editTextNumber.getText()).toString().isEmpty()) {

            if (Objects.requireNonNull(binding.editTextEmail.getText()).toString().contains("@")) {
                return true;

            } else {
                binding.filledTextFieldEmail.setError("Error in Email Address");
                return false;
            }
        } else {
            Toast.makeText(this, "Please, enter empty filed", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onClick(View v) {
        if (checkUserInputs()) {
            String name = Objects.requireNonNull(binding.editTextName.getText()).toString();
            String email = Objects.requireNonNull(binding.editTextEmail.getText()).toString();
            String address = Objects.requireNonNull(binding.editTextAddress.getText()).toString();
            String number = Objects.requireNonNull(binding.editTextNumber.getText()).toString();

            presenter.saveContactDetails(new Contact(name, email, address, number));
        }
    }

    @Override
    public void finishedOperation() {
        binding.editTextName.setText("");
        binding.editTextEmail.setText("");
        binding.editTextAddress.setText("");
        binding.editTextNumber.setText("");
    }
}