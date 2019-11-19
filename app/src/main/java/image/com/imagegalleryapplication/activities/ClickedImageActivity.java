package image.com.imagegalleryapplication.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import image.com.imagegalleryapplication.R;
import image.com.imagegalleryapplication.utils.AppController;

public class ClickedImageActivity extends AppCompatActivity {

    private NetworkImageView ivClickedImage;
    private ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_image);

        ivClickedImage = findViewById(R.id.iv_clicked_image);
        if (getIntent().getExtras()!=null){
            String imageUrl=getIntent().getExtras().getString("imageUrl");
            ivClickedImage.setImageUrl(imageUrl, imageLoader);
        }


    }
}
