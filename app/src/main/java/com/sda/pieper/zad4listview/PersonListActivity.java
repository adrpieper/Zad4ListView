package com.sda.pieper.zad4listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class PersonListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        ListView listView = (ListView) findViewById(R.id.person_list);
        final EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        final EditText surnameEditText = (EditText) findViewById(R.id.surname_edit_text);
        Button addButton = (Button) findViewById(R.id.add_button);

        PersonProvider personProvider = new PersonProvider();
        final List<Person> persons = personProvider.getPersons();
        final PersonListAdapter adapter = new PersonListAdapter(persons);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = new Person();
                person.setName(nameEditText.getText().toString());
                person.setSurname(surnameEditText.getText().toString());
                nameEditText.setText("");
                surnameEditText.setText("");
                persons.add(person);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                persons.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
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
        public View getView(int position, View oldView, ViewGroup viewGroup) {
            TextView textView;
            if (oldView != null) {
                textView = (TextView) oldView;
            }else {
                textView = new TextView(PersonListActivity.this);
            }

            Person person = persons.get(position);
            textView.setText(person.getName() + " " + person.getSurname());
            return textView;
        }
    }
}
