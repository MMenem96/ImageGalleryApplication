package image.com.imagegalleryapplication.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import image.com.imagegalleryapplication.R;
import image.com.imagegalleryapplication.adapters.ImageAdapter;
import image.com.imagegalleryapplication.utils.AppController;
import image.com.imagegalleryapplication.models.ImageModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getClass().getSimpleName();
    private GridView gridview;
    private ImageAdapter imageAdapter;
    private String url = "http://192.168.1.7/imgs/imgs.php";
    private ProgressDialog pDialog;
    private List<ImageModel> imageModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        gridview = findViewById(R.id.gridview);
        imageAdapter = new ImageAdapter(this);
        gridview.setAdapter(imageAdapter);
        JsonArrayRequest imageRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                ImageModel imageModel = new ImageModel();
                                imageModel.setId(obj.getInt("id"));
                                imageModel.setImg_url(obj.getString("img_url"));

                                // adding image to images array
                                imageModelList.add(imageModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        imageAdapter.setImageModelList(imageModelList);
                        imageAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding image request to request queue
        AppController.getInstance().addToRequestQueue(imageRequest);

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
}
