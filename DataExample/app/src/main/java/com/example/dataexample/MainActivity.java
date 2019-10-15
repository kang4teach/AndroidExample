package com.example.dataexample;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button internalFileBtn, externalFileBtn,xmlBtn;
    private int requestCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        internalFileBtn = findViewById(R.id.internalFileBtn);
        internalFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteAndReadInternalFile();
            }
        });

        externalFileBtn = findViewById(R.id.externalFileBtn);
        externalFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 动态获取读写权限
                // 1、 检查权限
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    // 2、 如果有权限，完成操作
                    WriteAndReadExternalFile();
                }else{
                    // 3、 没有权限
                    // 3.1 试图获取权限时被拒绝过，提供权限的解释内容（可选）
                    if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        Toast.makeText(view.getContext(),"需要读写SD卡",Toast.LENGTH_LONG).show();
                    }
                    // 3.2 请求权限
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},1);
                }
            }
        });

        xmlBtn = findViewById(R.id.xmlBtn);
        xmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WriteAndReadXml();
            }
        });
    }

    private void WriteAndReadXml() {
        //FileInputStream fileInputStream = openFileInput("data.xml");
        try {
            // 1、通过字符串的方式来写xml文件
            String content = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n" +
                    "<data>\n" +
                    "<EditText>Name</EditText>\n" +
                    "</data>";
            String s1 = "<?xml version='1.0' encoding='utf-8' standalone='yes' ?>\n";
            String s2 = "<EditText>Name</EditText>\n" ;
            // 写文件 。。。

            // 2、XmlSerializer
            FileOutputStream fileOutputStream = openFileOutput("data.xml",MODE_PRIVATE);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            xmlSerializer.setOutput(fileOutputStream,"utf-8");
            xmlSerializer.startDocument(null,true);// standalone
            xmlSerializer.startTag(null,"EditText");
            xmlSerializer.text(editText.getText().toString());
            xmlSerializer.endTag(null,"EditText");
            xmlSerializer.endDocument();
            fileOutputStream.close();

            FileInputStream fileInputStream = openFileInput("data.xml");
            XmlPullParser xmlPullParser = Xml.newPullParser();
            xmlPullParser.setInput(fileInputStream,"utf-8");
            int type = xmlPullParser.getEventType();
            while (type!= XmlPullParser.END_DOCUMENT){
                if (type == XmlPullParser.START_TAG){
                    if(xmlPullParser.getName().equals("EditText")){
                        String str = xmlPullParser.nextText();
                        // xmlPullParser.next();
                        //str = xmlPullParser.text();
                        textView.setText(str);
                    }
                }
                type = xmlPullParser.next();
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        if (requestCode == this.requestCode){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                WriteAndReadExternalFile();
            }
        }
    }

    private void WriteAndReadInternalFile()
    {
        try {
            FileOutputStream fileOutputStream = openFileOutput("data",MODE_APPEND);
            fileOutputStream.write(editText.getText().toString().getBytes());
            fileOutputStream.close();

            FileInputStream fileInputStream = openFileInput("data");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            textView.setText(bufferedReader.readLine());
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private void WriteAndReadExternalFile()
    {
        File path = Environment.getExternalStorageDirectory();
        File file = new File(path,"external_data");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(editText.getText().toString().getBytes());
            fileOutputStream.close();

            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            textView.setText(bufferedReader.readLine());
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
