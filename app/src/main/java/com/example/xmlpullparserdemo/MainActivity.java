package com.example.xmlpullparserdemo;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.text_id);

        InputStream stream = this.getResources().openRawResource(R.raw.students);
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlPullParserFactory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(stream, null);
            String tag_name = " ", text = " ";
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                tag_name = parser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text=parser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        switch (tag_name)
                        {
                            case "name":
                                t1.append("Name   :"+text+"\n");
                                break;
                            case "rollno":
                                t1.append("Rollno :"+text+"\n");
                                break;
                            case "age":
                                t1.append("Age    :"+text+"\n");
                                break;
                        }
                        break;
                }
                event=parser.next();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}