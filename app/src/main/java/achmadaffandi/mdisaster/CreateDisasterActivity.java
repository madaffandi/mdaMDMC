package achmadaffandi.mdisaster;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import achmadaffandi.mdisaster.Model.DisasterData;

public class CreateDisasterActivity extends AppCompatActivity {

    private Button btn_backInitateDis, btn_createDis;
    private EditText et_cd_calendar, et_ketLainDis;
    private AutoCompleteTextView cd_jenisbahaya;
    private String[] arrJenisBahaya;
    private String ketLainDis, jenisBahaya, jenisBencana, tglKejadian, lokKejadian;
    private DatabaseReference mDatabase;
    private TextView tvDisType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_disaster);
        btn_backInitateDis = (Button) findViewById(R.id.btn_backInitiateDis);
        btn_createDis = (Button) findViewById(R.id.btn_createDis);
        et_cd_calendar = (EditText) findViewById(R.id.et_cd_calendar);
        cd_jenisbahaya = (AutoCompleteTextView) findViewById(R.id.cd_jenisbahaya);
        arrJenisBahaya = getResources().getStringArray(R.array.jenis_bahaya);
        et_ketLainDis = (EditText) findViewById(R.id.et_ketLainCreateDis);
        tvDisType = (TextView) findViewById(R.id.tv_distype);

        Intent i = getIntent();
        String disType = i.getStringExtra(InitiateDisasterActivity.KEY_DISTYPE);
        setJenisBencana(disType);
        tvDisType.setText(disType);

        et_cd_calendar.setInputType(InputType.TYPE_NULL);
        et_cd_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(et_cd_calendar);
            }
        });

        ArrayAdapter<String> adapJeniSBahaya = new ArrayAdapter<String>(CreateDisasterActivity.this,
                android.R.layout.simple_list_item_1, arrJenisBahaya);
        cd_jenisbahaya.setAdapter(adapJeniSBahaya);
        cd_jenisbahaya.setThreshold(1);
        cd_jenisbahaya.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cd_jenisbahaya.showDropDown();
                }
            }
        });

        btn_createDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewDisaster();
            }
        });

        btn_backInitateDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateDisasterActivity.this, InitiateDisasterActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    private void showDateDialog(final EditText date_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                date_in.setText(simpleDateFormat.format(calendar.getTime()));
                setTglKejadian(date_in.getText().toString());
            }
        };

        new DatePickerDialog(CreateDisasterActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void createNewDisaster() {
        setKetLainDis(et_ketLainDis.getText().toString());
        setJenisBahaya(cd_jenisbahaya.getText().toString());
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Disaster").push();
        DisasterData dData = new DisasterData(getJenisBencana(), getTglKejadian(), getJenisBahaya(), getKetLainDis());
        mDatabase.setValue(dData);
        Toast.makeText(CreateDisasterActivity.this, "Data bencana baru telah ditambahkan", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CreateDisasterActivity.this, DisListActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    public String getKetLainDis() {
        return ketLainDis;
    }

    public void setKetLainDis(String ketLainDis) {
        this.ketLainDis = ketLainDis;
    }

    public String getJenisBahaya() {
        return jenisBahaya;
    }

    public void setJenisBahaya(String jenisBahaya) {
        this.jenisBahaya = jenisBahaya;
    }

    public String getJenisBencana() {
        return jenisBencana;
    }

    public void setJenisBencana(String jenisBencana) {
        this.jenisBencana = jenisBencana;
    }

    public String getTglKejadian() {
        return tglKejadian;
    }

    public void setTglKejadian(String tglKejadian) {
        this.tglKejadian = tglKejadian;
    }

    public String getLokKejadian() {
        return lokKejadian;
    }

    public void setLokKejadian(String lokKejadian) {
        this.lokKejadian = lokKejadian;
    }
}