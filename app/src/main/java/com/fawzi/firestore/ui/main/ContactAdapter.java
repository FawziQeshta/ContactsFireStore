package com.fawzi.firestore.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fawzi.firestore.databinding.ItemContactBinding;
import com.fawzi.firestore.models.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    private Context context;
    private List<Contact> data;

    public ContactAdapter(Context context, List<Contact> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactHolder(ItemContactBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = data.get(position);

        holder.binding.name.setText(contact.getName());
        holder.binding.email.setText(contact.getEmail());
        holder.binding.address.setText(contact.getAddress());
        holder.binding.number.setText(contact.getNumber());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ContactHolder extends RecyclerView.ViewHolder {

        private ItemContactBinding binding;

        public ContactHolder(ItemContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
