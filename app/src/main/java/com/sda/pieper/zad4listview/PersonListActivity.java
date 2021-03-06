package com.sda.pieper.zad4listview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonListActivity extends Activity {

    private EditText nameEditText;
    private ListView listView;
    private EditText surnameEditText;
    private Button addButton;
    private List<Person> persons;
    private PersonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);
        listView = (ListView) findViewById(R.id.person_list);
        nameEditText = (EditText) findViewById(R.id.name_edit_text);
        surnameEditText = (EditText) findViewById(R.id.surname_edit_text);
        addButton = (Button) findViewById(R.id.add_button);

        PersonProvider personProvider = new PersonProvider();
        persons = personProvider.getPersons();
        adapter = new PersonListAdapter(persons);
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

        Switch sortSwitch = (Switch) findViewById(R.id.sorting_order_switch);
        sortSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                sort(checked);
            }
        });
    }

    private void sort(boolean order) {
        Log.d("Switch", "checked " + order);

        final Comparator<Person> comparator = new Comparator<Person>() {
            @Override
            public int compare(Person firstPerson, Person secondPerson) {
                int result = firstPerson.getSurname().compareTo(secondPerson.getSurname());

                if (result == 0) {
                    return firstPerson.getName().compareTo(secondPerson.getName());
                } else {
                    return result;
                }
            }
        };
        if (order) {
            Collections.sort(persons, comparator);
        }else {
            Comparator<Person> descComparator = new Comparator<Person>() {
                @Override
                public int compare(Person p1, Person p2) {
                    return comparator.compare(p1,p2) * -1;
                }
            };
            Collections.sort(persons, descComparator);
        }

        adapter.notifyDataSetChanged();
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
