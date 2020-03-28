package civilize.useradmin;

public class SalePage {
    private int page_id;
    private String page_name;
    private int u_id;
    private String status;

    public SalePage(int page_id, String page_name, int u_id, String status){
        this.page_id = page_id;
        this.page_name = page_name;
        this.u_id = u_id;
        this.status = status;
    }

    public int getPage_id(){
        return page_id;
    }

    public void setPage_id(int page_id){
        this.page_id = page_id;
    }

    public String getPage_name(){
        return page_name;
    }

    public void setPage_name(String page_name){
        this.page_name = page_name;
    }

    public int getU_id(){
        return u_id;
    }

    public void setU_id(int u_id){
        this.u_id = u_id;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

}