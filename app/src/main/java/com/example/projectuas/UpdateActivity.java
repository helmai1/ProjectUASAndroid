package com.example.projectuas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.projectuas.Upload;

import static android.text.TextUtils.isEmpty;

public class UpdateActivity extends AppCompatActivity {
    private DatabaseReference database;
    private FirebaseAuth auth;

    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private TextView mTextViewShowUploads;
    private EditText mEditTextFileName;
    private EditText mDeskripsi;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private String cekFile, cekDeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        getSupportActionBar().setTitle("Update Data");

        mButtonChooseImage = findViewById(R.id.button_choose_image_update);
        mButtonUpload = findViewById(R.id.button_upload_update);
        mTextViewShowUploads = findViewById(R.id.text_view_show_uploads_update);
        mEditTextFileName = findViewById(R.id.edit_text_file_name_update);
        mImageView = findViewById(R.id.image_view_update);
        mProgressBar = findViewById(R.id.progress_bar_update);
        mDeskripsi = findViewById(R.id.deskripsi_update);

        //Mendapatkan Instance autentikasi dan Referensi dari Database
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mendapatkan Data Mahasiswa yang akan dicek
                cekDeskripsi = mDeskripsi.getText().toString();
                cekFile = mEditTextFileName.getText().toString();
                //Mengecek agar tidak ada data yang kosong, saat proses update
                if(isEmpty(cekDeskripsi) || isEmpty(cekFile) ){
                    Toast.makeText(UpdateActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    //Menjalankan proses update data
                    Upload setUpload = new Upload();
                    setUpload.setDeskripsi(mDeskripsi.getText().toString());
                    setUpload.setName(mEditTextFileName.getText().toString());
                    updateMahasiswa(setUpload);
                }
            }


        });
    }

    private void getData() {
        final String getDeskripsi = getIntent().getExtras().getString("dataDeskripsi");
        final String getFileName = getIntent().getExtras().getString("dataNamaFile");

        mDeskripsi.setText(getDeskripsi);
        mEditTextFileName.setText(getFileName);

    }
    private void updateMahasiswa(Upload mahasiswa){
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("uploads")
                .child(getKey)
                .setValue(mahasiswa)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        mDeskripsi.setText("");
                        mEditTextFileName.setText("");
                        Toast.makeText(UpdateActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
