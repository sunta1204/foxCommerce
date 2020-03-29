package civilize.useradmin;

public class User{
    private int u_id;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String expiry_date;
    private String type;
    private String Inspector;

    public User(int u_id, String username, String password, String email, String tel, String expiry_date, String type, String Inspector){
        this.u_id = u_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.expiry_date =expiry_date;
        this.type = type;
        this.Inspector = Inspector;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id){
        this.u_id = u_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel){
        this.tel = tel;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date){
        this.expiry_date = expiry_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getInspector() {
        return Inspector;
    }

    public void setInspector(String Inspector){
        this.Inspector = Inspector;
    }

}