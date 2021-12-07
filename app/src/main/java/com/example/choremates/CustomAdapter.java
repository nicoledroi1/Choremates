//package com.example.choremates;
//
//import android.content.Context;
//import android.media.Image;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class CustomAdapter extends ArrayAdapter<Chore> implements View.OnClickListener {
//
//    ArrayList<Chore> choreSet;
//    Context context;
//    public static ImageView delete;
//    ImageView image;
//    String email;
//    TextView blurb;
//    public static int position1;
//
//
//    // View lookup cache
//    private static class ViewHolder {
//        TextView name;
//        ImageView icon;
//    }
//
//    public CustomAdapter(ArrayList<Chore> chore, Context context, String e, TextView t) {
//        super(context, R.layout.custom_row, chore);
//        this.choreSet = chore;
//        this.context = context;
//        blurb = t;
//        email = e;
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    private int lastPosition = -1;
//    private static boolean edit;
//
//
//
//    private ImageView getImage(ImageView i){
//        return i;
//    }
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        // Get the data item for this position
//        final Chore ch = getItem(position);
//        // Check if an existing view is being reused, otherwise inflate the view
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(R.layout.custom_row, null);
//        image = convertView.findViewById(R.id.icon);
//        TextView title = convertView.findViewById(R.id.name);
//        delete = convertView.findViewById(R.id.delete);
//
//        if (edit){
//            delete.setVisibility(View.VISIBLE);
//            delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DatabaseHelper db = new DatabaseHelper(context);
//                    db.deleteChore(email, ch.getName());
//                    choreSet.remove(position);
//                    if (choreSet.size() ==0 ) {
//                        blurb.setVisibility(View.VISIBLE);
//                    }
//                    Toast.makeText(context, "Chore deleted", Toast.LENGTH_LONG).show();
//                    notifyDataSetChanged();
//                }
//            });
//        }
//        image.setImageResource(ch.getImage());
//        title.setText(ch.getName());
//
//
//        return convertView;
//    }
//    public void edit(boolean s){ edit = s; }
//
//    public static int getSize(){
//        return position1;
//    }
//}
