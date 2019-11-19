package image.com.imagegalleryapplication.models;

public class ImageModel {

    private int id;
    private String img_url;

    public ImageModel(int id, String img_url) {
        this.id = id;
        this.img_url = img_url;
    }

    public ImageModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
