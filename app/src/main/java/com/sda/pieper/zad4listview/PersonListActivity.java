package com.sda.pieper.zad4listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class PersonListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        ListView listView = (ListView) findViewById(R.id.person_list);
        PersonProvider personProvider = new PersonProvider();
        List<Person> persons = personProvider.getPersons();
        PersonListAdapter adapter = new PersonListAdapter(persons);
        listView.setAdapter(adapter);
    }

    class PersonListAdapter extends BaseAdapter {
        private List<Person> persons;

        public PersonListAdapter(List<Person> persons) {
            this.persons = persons;
        }

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return persons.get(position);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(PersonListActivity.this);
            Person person = persons.get(position);
            textView.setText(person.getName() + " " + person.getSurname());
            return textView;
        }
    }
}
