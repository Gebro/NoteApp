package com.course.lab8;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.course.lab8.DataBase.Note;

import java.util.Calendar;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder>  {
	
    Note[]items;
    Context context;
    public NotesAdapter(Note[] items){
        this.items = items;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		
        holder.TV_title.setText(items[position].title);
        holder.TV_desc.setText(items[position].description);
        if(position%2==0){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        }
        else{
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(items[position].date);
        String date = calendar.get(Calendar.YEAR)+" - "+
                (calendar.get(Calendar.MONTH)+1)+" - "+calendar.get(Calendar.DAY_OF_MONTH);
        holder.TV_date.setText(date);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
		
        TextView TV_title;
        TextView TV_date;
        TextView TV_desc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TV_title = itemView.findViewById(R.id.TV_title);
            TV_date = itemView.findViewById(R.id.TV_date);
            TV_desc = itemView.findViewById(R.id.TV_desc);
        }
    }
}
