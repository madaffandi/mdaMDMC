package achmadaffandi.mdamdmc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DisDetailActivity extends AppCompatActivity {

    private TextView tv_judulDisaster, tv_tglKejadian, tv_lokKejadian, tv_jenisBahaya, tv_ketLain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dis_detail);

        tv_judulDisaster = (TextView) findViewById(R.id.tv_judulDisaster);
        tv_tglKejadian = (TextView) findViewById(R.id.tv_tglKejadian);
        tv_lokKejadian = (TextView) findViewById(R.id.tv_lokKejadian);
        tv_jenisBahaya = (TextView) findViewById(R.id.tv_jenisBahaya);
        tv_ketLain = (TextView) findViewById(R.id.tv_ketLain);

    }
}