package achmadaffandi.mdamdmc.Model;

public class DisasterData {
    private String title, description, imageId;
    private String jenisBencana, lokasiKejadian, tanggalKejadian, jenisBahaya, keteranganLain;

    public DisasterData(String title, String description, String imageId) {
        this.title = title;
        this.description = description;
        this.imageId = imageId;
    }

    public DisasterData(String jenisBencana, String tanggalKejadian, String jenisBahaya, String keteranganLain){
        this.jenisBencana = jenisBencana;
        //this.lokasiKejadian = lokasiKejadian;
        this.tanggalKejadian = tanggalKejadian;
        this.jenisBahaya = jenisBahaya;
        this.keteranganLain = keteranganLain;
    }

    public DisasterData() {

    }

    public String getJenisBencana() {
        return jenisBencana;
    }

    public void setJenisBencana(String jenisBencana) {
        this.jenisBencana = jenisBencana;
    }

    public String getLokasiKejadian() {
        return lokasiKejadian;
    }

    public void setLokasiKejadian(String lokasiKejadian) {
        this.lokasiKejadian = lokasiKejadian;
    }

    public String getTanggalKejadian() {
        return tanggalKejadian;
    }

    public void setTanggalKejadian(String tanggalKejadian) {
        this.tanggalKejadian = tanggalKejadian;
    }

    public String getJenisBahaya() {
        return jenisBahaya;
    }

    public void setJenisBahaya(String jenisBahaya) {
        this.jenisBahaya = jenisBahaya;
    }

    public String getKeteranganLain() {
        return keteranganLain;
    }

    public void setKeteranganLain(String keteranganLain) {
        this.keteranganLain = keteranganLain;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}
