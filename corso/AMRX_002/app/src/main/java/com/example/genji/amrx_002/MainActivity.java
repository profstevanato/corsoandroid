package com.example.genji.amrx_002;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class MainActivity extends AppCompatActivity {

    private final static String MY_LOG = "MY_LOG";

    // initialize value

    Thing t1 = new Thing(1, "t1", true);
    Thing t2 = new Thing(2, "t2", false);
    Thing t3 = new Thing(3, "t3", true);
    Thing t4 = new Thing(4, "t4", false);
    Thing t5 = new Thing(5, "t5", true);

    TextView t1View;
    TextView t2View;
    TextView t3View;
    TextView t4View;
    TextView t5View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1View = (TextView) findViewById(R.id.t1);
        t2View = (TextView) findViewById(R.id.t2);
        t3View = (TextView) findViewById(R.id.t3);
        t4View = (TextView) findViewById(R.id.t4);
        t5View = (TextView) findViewById(R.id.t5);

        updateText();

        final Switch switchBtn = (Switch) findViewById(R.id.boolean_switch_btn);

        switchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // the observable
                    Observable myObservable = Observable.just(t1, t2, t3, t4, t5);
                    if (switchBtn.isChecked()) {
                        switchBtn.setChecked(true);
                        myObservable.map(transformThing(true))
                                .subscribe();
                        Log.d(MY_LOG, t2.getVal()+""); // if TRUE all works
                        updateText();
                    } else {
                        myObservable.map(transformThing(false))
                                .subscribe();
                        Log.d(MY_LOG, t2.getVal()+""); // if TRUE all works
                        updateText();
                    }
                }
            }
        );
    }

    private void updateText(){
        t1View.setText(String.valueOf(t1.getVal()));
        t2View.setText(String.valueOf(t2.getVal()));
        t3View.setText(String.valueOf(t3.getVal()));
        t4View.setText(String.valueOf(t4.getVal()));
        t5View.setText(String.valueOf(t5.getVal()));
    }

    private Function<Thing, Thing> transformThing(Boolean b) {
        return filteredObject -> {
            filteredObject.setVal(b);
            return filteredObject;
        };
    }
}
