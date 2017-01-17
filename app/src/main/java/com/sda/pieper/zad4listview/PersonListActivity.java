package com.sda.pieper.zad4listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class PersonListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        ListView listView = (ListView) findViewById(R.id.person_list);
    }
}
