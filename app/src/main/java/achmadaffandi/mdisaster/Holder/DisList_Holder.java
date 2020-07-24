package achmadaffandi.mdisaster.Holder;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import achmadaffandi.mdisaster.R;

public class DisList_Holder extends RecyclerView.ViewHolder {

    View mView;

    public DisList_Holder(View itemView) {
        super(itemView);
        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());

            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });
    }

    private DisList_Holder.ClickListener mClickListener;

    //callback interface
    public interface ClickListener{
        public void onItemClick(View view, int position);
        public void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(DisList_Holder.ClickListener clickListener){
        mClickListener = clickListener;
    }

    public void setTitle(String title) {
        TextView post_title = (TextView) mView.findViewById(R.id.dis_title);
        post_title.setText(title);
    }

    public void setDesc(String desc) {
        TextView post_title = (TextView) mView.findViewById(R.id.dis_desc);
        post_title.setText(desc);
    }

    public void setImage(Context ctx, String image) {
        /*
        ImageView post_img = (ImageView) mView.findViewById(R.id.dis_img);
        Picasso.with(ctx).load(image).into(post_img);


        Context c = this.itemView.getContext();
        int img_id = c.getResources().getIdentifier("drawable/" + imageId, null, c.getPackageName());
        ImageView post_img = (ImageView) mView.findViewById(R.id.dis_img);
        post_img.setImageResource(img_id);
        */
    }
}