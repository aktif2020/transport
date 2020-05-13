package com.safari.demo_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.google.zxing.BarcodeFormat;
import com.mobiwire.CSAndroidGoLib.AndroidGoCSApi;
import com.mobiwire.CSAndroidGoLib.CsPrinter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout title, name, first;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new AndroidGoCSApi(this);

        this.title = (TextInputLayout) findViewById(R.id.agence);
        this.name = (TextInputLayout) findViewById(R.id.name);
        this.first = (TextInputLayout) findViewById(R.id.firstname);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void print(View view){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String sTitle = title.getEditText().getText().toString().trim();
        String sName = name.getEditText().getText().toString().trim();
        String sFirst = first.getEditText().getText().toString().trim();

        CsPrinter printer = new CsPrinter();
        printer.addTextToPrint(sTitle, null, 50, true, false, 1);
        printer.addTextToPrint("av. frontier N 163 C/kalamu Q/Kauka", null,20, true, false, 1);
        printer.addTextToPrint("+243825341040", null, 24, false, true, 1);
        printer.addTextToPrint(dateTimeFormatter.format(now), "B613", 24, true, false, 3);
        printer.addTextToPrint("- - - - - - - - - - - - - - - - - - - - - - - - ", "", 30, false, false, 1);
        printer.addTextToPrint("Noms ", sName + " "+sFirst, 24, true, false, 8);
        printer.addTextToPrint("Trajet: " ,"KIN-MATADI", 24, true,  false, 8);

        printer.addTextToPrint("- - - - - - - - - - - - - - - - - - - - - - - - ", "", 30, false, false, 1);
        printer.addTextToPrint("date voyage : ", "28.05.2020", 24, true, false, 8);
        printer.addTextToPrint("depart : ", "07h30", 24, true, false, 8);
        printer.addTextToPrint("expire le 20.05.2020 14:00", null, 24, true, false, 3);
        printer.addTextToPrint("", "", 30, true, false, 1);
        printer.addBarQrCodeToPrint(sTitle+sName+sFirst, BarcodeFormat.QR_CODE, 384, 280);
        printer.print(this);
    }


}
