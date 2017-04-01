package dsardy.in.chalkcards;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shubham on 4/1/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    List<Bitmap> bitmaplist = new ArrayList<>();
    Context context;

    public DataAdapter(Context applicationContext, List<Bitmap> bitmaplist) {

        this.context = applicationContext;
        this.bitmaplist = bitmaplist;
    }




    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {

        holder.imageView.setImageBitmap(bitmaplist.get(position));

    }

    @Override
    public int getItemCount() {
        return bitmaplist.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        ImageView imageView ;


        public ViewHolder(View view) {
            super(view);

            //declare

            imageView = (ImageView) view.findViewById(R.id.imageViewcard);


        }


    }
}
