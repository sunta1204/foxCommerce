package civilize.useradmin;

public class ImageContent{
    private int img_id;
    private String img_file_name;
    private int page_id;

    public ImageContent(int img_id, String  img_file_name, int page_id){
        this.img_id = img_id;
        this.img_file_name = img_file_name;
        this.page_id = page_id;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    
}