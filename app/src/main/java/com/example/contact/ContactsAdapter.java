package com.example.contact;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.contactViewHolder>{
    private ArrayList<String> contacts=new ArrayList<>();
    private static final String TAG="ContactsAdapter";
    private ItemEventListener itemEventListener;

    public ContactsAdapter(ItemEventListener itemEventListener){
        this.itemEventListener=itemEventListener;
        contacts.add("Ruthann Trustrie");
        contacts.add("Peadar Dawtrey");
        contacts.add("Felipe Bradtke");
        contacts.add("Claude Crissil");
        contacts.add("Jacky Girardeau");
        contacts.add("Rubia Dominguez");
        contacts.add("Michaela Churchley");
        contacts.add("Harvey Pentelow");
        contacts.add("Neilla Langton");
        contacts.add("Marco Greaves");
        contacts.add("Liz Batchley");
        contacts.add("Lamond Littlepage");
        contacts.add("Malina Weir");
        contacts.add("Tomlin Lenchenko");
        contacts.add("Hy Pavelin");
        contacts.add("Jenelle Palin");
        contacts.add("Damon Knewstubb");
        contacts.add("Alex Ivanusyev");
        contacts.add("Hamil Callery");
        contacts.add("Karol Syer");
    }

    public void addNewContact(String fullName){
        contacts.add(0,fullName);
        notifyItemInserted(0);
    }

    public void updateContact(String fullName,int position){
        contacts.set(position,fullName);
        notifyItemChanged(position);
    }

    @NonNull
    @Override
    //there we must create a viewholder for our method, if we have 5 contacts so there would be 5 viewholder bper each contact
    public contactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG,"onCreateViewHolder: ");
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new contactViewHolder(view);
    }

    @Override
    // when the user scroll the page this method is call because the new items will appears in the page
    public void onBindViewHolder(@NonNull contactViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder: position=>" +position);
        holder.bindContact(contacts.get(position));
    }

    @Override
    //it modefy the number of items
    public int getItemCount() {
        return contacts.size();
    }

    public class contactViewHolder extends RecyclerView.ViewHolder{
        private TextView firstCharacterTv;
        private  TextView fullnameTv;
        public contactViewHolder(@NonNull View itemView) {
            super(itemView);
            firstCharacterTv=itemView.findViewById(R.id.tv_contact_firstCharacter);
            fullnameTv=itemView.findViewById(R.id.tv_contact_fullName);
        }

        public void bindContact(final String fullname){
            fullnameTv.setText(fullname);
            firstCharacterTv.setText(fullname.substring(0,1));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemEventListener.onItemClick(fullname,getAdapterPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    contacts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return false;
                }
            });
        }

    }
    public interface ItemEventListener{
        void onItemClick(String fullName,int position);
    }

}
