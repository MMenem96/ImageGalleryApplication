package image.com.imagegalleryapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import image.com.imagegalleryapplication.R;
import image.com.imagegalleryapplication.activities.ClickedImageActivity;
import image.com.imagegalleryapplication.models.ImageModel;
import image.com.imagegalleryapplication.utils.AppController;

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ImageModel> imageModelList = new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ImageAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return imageModelList.size();
    }

    @Override
    public ImageModel getItem(int position) {
        return imageModelList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (mInflater == null)
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mInflater.inflate(R.layout.main_image_gallery_card, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        NetworkImageView thumbNail = convertView
                .findViewById(R.id.iv_image);
        ImageModel imageModel = imageModelList.get(position);
        // thumbnail image
        Log.e("imgurl", imageModel.getImg_url());
        thumbNail.setImageUrl(imageModel.getImg_url(), imageLoader);

        thumbNail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClickedImageActivity.class);
                intent.putExtra("imageUrl", imageModelList.get(position).getImg_url());
                context.startActivity(intent);
            }
        });
        return convertView;
    }


    public void setImageModelList(List<ImageModel> imageModelList) {
        this.imageModelList = imageModelList;
        notifyDataSetChanged();
    }
}
