package achmadaffandi.mdamdmc.Model;

public class User {
    public String nama, email, phone, type;

    public User() {

    }

    public User(String nama, String email, String phone, String type) {
        this.nama = nama;
        this.email = email;
        this.phone = phone;
        this.type = type;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
