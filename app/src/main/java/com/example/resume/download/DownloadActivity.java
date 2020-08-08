package com.example.resume.download;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.resume.R;
import com.example.resume.download.adapter.MusicItemsAdapter;
import com.example.resume.download.database.DBHelper;
import com.example.resume.download.models.FileInfo;
import com.example.resume.download.models.Music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class DownloadActivity extends AppCompatActivity {

    String TAG = DownloadActivity.class.getSimpleName();
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        getSupportActionBar().hide();

        ImageView pdf1 = findViewById(R.id.pdfimg1);
        ImageView pdf2 = findViewById(R.id.pdfimg2);
        ImageView pdf3 = findViewById(R.id.pdfimg3);
        ImageView pdf4 = findViewById(R.id.pdfimg4);
        ImageView pdf5 = findViewById(R.id.pdfimg5);
        ImageView pdf6 = findViewById(R.id.pdfimg6);
        pdf1.setImageResource(R.drawable.a);
        pdf2.setImageResource(R.drawable.b);
        pdf3.setImageResource(R.drawable.c);
        pdf4.setImageResource(R.drawable.d);
        pdf5.setImageResource(R.drawable.e);
        pdf6.setImageResource(R.drawable.f);
        GridView gvMusics = findViewById(R.id.gvMusics);
        final ArrayList<Music> musics = new ArrayList<>();
        musics.add(new Music("شهاب مظفری", "http://irsv.upmusics.com/Downloads/Musics/Shahab%20Mozaffari%20-%20Setayesh%20(320).mp3", R.drawable.shahabmo));
        musics.add(new Music("امیر عظیمی", "http://irsv.upmusics.com/Downloads/Musics/Amir%20Azimi%20%E2%80%93%20Rubah%20(320).mp3", R.drawable.amirazimi));
        musics.add(new Music("مهدی احمدوند", "http://irsv.upmusics.com/Downloads/Musics/Mehdi%20Ahmadvand%20-%20Khiyal%20(320).mp3", R.drawable.mehdiahmad));
        musics.add(new Music("رضا بهرام", "http://irsv.upmusics.com/Downloads/Musics/Reza%20Bahram%20%7C%20Adelane%20Nist%20demo(320).mp3", R.drawable.rezabahram));
        musics.add(new Music("رضا شیری", "http://irsv.upmusics.com/Downloads/Musics/Reza%20Shiri%20%7C%20Dard%20Nashid%20demo%20(128).mp3", R.drawable.rezashiri));
        musics.add(new Music("امین حبیبی", "http://irsv.upmusics.com/Downloads/Musics/Amin%20Habibi%20-%20Nime%20Joon%20(320).mp3", R.drawable.aminhabibi));
        musics.add(new Music("حجت اشرف زاده", "http://irsv.upmusics.com/Downloads/Musics/Hojat%20Ashrafzadeh%20-%20Mahe%20Bi%20Tekrar%20(320).mp3", R.drawable.hoajt));
        musics.add(new Music("مسعود صادقلو", "http://irsv.upmusics.com/Downloads/Musics/Masoud%20Sadeghloo%20-%20Dorehami%20(320).mp3", R.drawable.masudsadeghlu));
        musics.add(new Music("ایهام", "http://irsv.upmusics.com/Downloads/Musics/Ehaam%20%7C%20Soltane%20Ghalbe(320).mp3", R.drawable.eham));

        MusicItemsAdapter adapter = new MusicItemsAdapter();
        adapter.setContext(this);
        adapter.setList(musics);
        gvMusics.setAdapter(adapter);
        gvMusics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String address = musics.get(position).getPath();
                new Downloader().execute(address);
            }
        });
        checkPermission();
    }


    public void checkPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {

                if ((grantResults.length > 0
                        && grantResults[0] != PackageManager.PERMISSION_GRANTED)) {

                    AlertDialog.Builder dialogBuilder;
                    AlertDialog dialog;
                    dialogBuilder = new AlertDialog.Builder(this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        dialogBuilder.setTitle("Permissions");
                        dialogBuilder.setMessage("This Permissions Are Necessary To Run This App!\nAre You Sure?");
                        dialogBuilder.setPositiveButton("Reconsider", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                checkPermission();
                            }

                        });
                        dialogBuilder.setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        dialog = dialogBuilder.create();
                        dialog.show();
                    }
                    return;

                }
            }
        }
    }

    public void pdf1click(View view) {
        String address = "https://cvbuilder.me/DownloadSampleResume/JobSearching-Sample-Resume-fa.pdf";


        new Downloader().execute(address);

    }

    public void pdf2onclick(View view) {
        String address = "http://dl3.takbook.com/pdf3/ebook9445[www.takbook.com].pdf";


        new Downloader().execute(address);
    }

    public void pdf3onclick(View view) {
        String address = "http://dl3.takbook.com/pdf3/ebook9443[www.takbook.com].pdf";


        new Downloader().execute(address);
    }

    public void pdf4onclick(View view) {
        String address = "http://dl3.takbook.com/pdf3/ebook9442[www.takbook.com].pdf";


        new Downloader().execute(address);
    }

    public void pdf5onclick(View view) {
        String address = "http://dl3.takbook.com/pdf3/ebook9440[www.takbook.com].pdf";


        new Downloader().execute(address);
    }

    public void pdf6onclick(View view) {
        String address = "http://dl3.takbook.com/pdf3/ebook9441[www.takbook.com].pdf";


        new Downloader().execute(address);
    }


    private class Downloader extends AsyncTask<String, Integer, String> {

        public Downloader() {
            progressDialog = new ProgressDialog(DownloadActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {


                String directory = "/sdcard/Exam_RegisterAndLogin/";
                File directoryfile = new File(directory);
                if (directoryfile.exists() == false || directoryfile.isDirectory() == false)
                    directoryfile.mkdir();
                URL url = new URL(params[0]);
                String filename = url.toString();
                String[] name = filename.split("/");
                String path = "/sdcard/Exam_RegisterAndLogin/" + name[name.length - 1];
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                int size = urlConnection.getContentLength();
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                File file = new File(path);
                if (file.exists() == false)
                    file.createNewFile();
                OutputStream outputStream = new FileOutputStream(path);

                byte[] buffer = new byte[1024];
                int count = -1;
                int total = 0;
                while ((count = inputStream.read(buffer)) != -1) {

                    total += count;

                    publishProgress(total * 100 / size);
                    outputStream.write(buffer, 0, count);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();

                int fileSize = (int) new File(path).length();
                String fname = name[name.length - 1];
                FileInfo info = new FileInfo(fileSize, fname, path);
                DBHelper helper = new DBHelper(DownloadActivity.this);
                helper.insert(info);

                return path;


            } catch (Exception e) {
                e.printStackTrace();

                Log.e(TAG, "faild");
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if (s != null) {
                Toast.makeText(DownloadActivity.this, "Download Compelete!", Toast.LENGTH_LONG).show();
                NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(DownloadActivity.this);
                notiBuilder.setAutoCancel(true);
                notiBuilder.setContentTitle("Downloaded File");
                notiBuilder.setContentText("Tap here to view path");
                notiBuilder.setSmallIcon(R.drawable.ic_nofiy_black_24dp);
                Intent intent = new Intent(DownloadActivity.this, ShowPathActivity.class);
                intent.putExtra("path", s);
                PendingIntent pendingIntent = PendingIntent.getActivities(DownloadActivity.this, 1, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);
                notiBuilder.setContentIntent(pendingIntent);

                NotificationChannel notificationChannel = null;
                NotificationManager notificationManager = (NotificationManager) DownloadActivity.this.getSystemService(NOTIFICATION_SERVICE);

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    notiBuilder.setChannelId("channel_id");
                    notificationChannel = new NotificationChannel("channel_id", "notfiychanel", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);

                }
                notificationManager.notify(0, notiBuilder.build());


            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}