package com.example.quizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {
    // The current state of the app
    private int mCurrentState;

    // TODO (3) Create an instance variable storing a Cursor called mData
    private Button mButton;

    // This state is when the word definition is hidden and clicking the button will therefore
    // show the definition
    private final int STATE_HIDDEN = 0;
private Cursor mData;
    // This state is when the word definition is shown and clicking the button will therefore
    // advance the app to the next word
    private final int STATE_SHOWN = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        mButton=findViewById ( R.id.button_next );
       new WordFetchTask().execute ( );
    }

    public void onButtonClick(View view) {

        // Either show the definition of the current word, or if the definition is currently
        // showing, move to the next word.
        switch (mCurrentState) {
            case STATE_HIDDEN:
                showDefinition();
                break;
            case STATE_SHOWN:
                nextWord();
                break;
        }
    }

    public void nextWord() {

        // Change button text
        mButton.setText(getString(R.string.show_definition));

        mCurrentState = STATE_HIDDEN;

    }

    public void showDefinition() {

        // Change button text
        mButton.setText(getString(R.string.next_word));

        mCurrentState = STATE_SHOWN;

    }
    public class WordFetchTask extends AsyncTask<void,void, Cursor>{



        @Override
        protected Cursor doInBackground(void... voids) {

            ContentResolver resolver=getContentResolver ();
            Cursor cursor=resolver.query ( DroidTermsExampleContract.CONTENT_URI ,
                    null,null ,null,null);

            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute ( cursor );
        mData=cursor;
        }
    }

}
