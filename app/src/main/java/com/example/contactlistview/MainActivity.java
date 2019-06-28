package com.example.contactlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ListView contactList;
    Button getcontacts;
    private int permission = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getcontacts = findViewById(R.id.getcontacts);
        contactList = findViewById(R.id.contactslist);
        getcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "YOU HAVE ALREADY GRANTED PERMISSION", Toast.LENGTH_SHORT).show();
                    getContacts();
                }
                else
                    {

                    permission_not_granted();
                }
            }


        });


    }

    private void permission_not_granted() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS))
        {

            new AlertDialog.Builder(this)
                    .setTitle("PERMISSION REQUIRED")
                    .setMessage("Permission is necessary to show contacts")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {

                            ActivityCompat.requestPermissions(MainActivity.this, new String []{Manifest.permission.READ_CONTACTS},permission);
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i)
                        {

                            dialogInterface.dismiss();
                        }
                    })
                    .create().show();

        }
        else
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},permission);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == permission) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void getContacts()
    {

        Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        startManagingCursor(cursor);
        final String[] From={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID};
      final  int[] to= {android.R.id.text1,android.R.id.text2};
       final SimpleCursorAdapter simpleCursorAdapter= new SimpleCursorAdapter(this,android.R.layout.simple_expandable_list_item_2,cursor,From,to);
        contactList.setAdapter(simpleCursorAdapter);
        contactList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                Toast.makeText(MainActivity.this, Sampler.Va,Toast.LENGTH_SHORT).show();
            }
        });

        {

        };
    }
}
