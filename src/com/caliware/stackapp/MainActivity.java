package com.caliware.stackapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

	private MainActivity instance;
	
	private EditText stackInputEditText;
	private Button pushButton;
	private Button popButton;
	private Button clearButton;
	private TextView stackTextView;

	private ArrayList<Integer> stackArrayList = new ArrayList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_main);

		setUpUiElements();
	}

	private void setUpUiElements()
	{
		stackInputEditText = (EditText) findViewById(R.id.activity_main_stack_input_edittext);
		pushButton = (Button) findViewById(R.id.activity_main_push_button);
		pushButton.setOnClickListener(this);
		popButton = (Button) findViewById(R.id.activity_main_pop_button);
		popButton.setOnClickListener(this);
		clearButton = (Button) findViewById(R.id.activity_main_clear_button);
		stackTextView = (TextView) findViewById(R.id.activity_main_stack_textview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch(v.getId())
		{
		case R.id.activity_main_push_button:
		{
			pushStack();
			break;
		}
		case R.id.activity_main_pop_button:
		{
			popStack();
			break;
		}
		case R.id.activity_main_clear_button:
		{
			clearStack();
			break;
		}
		}
	}

	private Boolean validateInput(String input){

		boolean success = false;

		String regex = "^[0-9]{1}$";

		stackInputEditText.setError(null);

		if(TextUtils.isEmpty(input) || !input.matches(regex))
		{
			stackInputEditText.setError(getString(R.string.invalid_stack_input));
			stackInputEditText.requestFocus();
		}
		else
		{
			success = true;
		}

		return success;
	}

	private void pushStack(){

		if(stackInputEditText != null)
		{
			String input = stackInputEditText.getText().toString();

			if(validateInput(input))
			{
				if(stackArrayList.size() < 3)
				{
					stackArrayList.add(Integer.valueOf(input));
					displayStack(false);
				}
				else
				{
//					errorPopUp(getString(R.string.push_error_full_stack));
					displayStack(true);
				}
			}
		}
	}

	private void popStack(){
		
		
	}

	private void clearStack(){

	}
	
	private void displayStack(boolean full)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if(stackArrayList.isEmpty())
		{
			stackTextView.setText(getString(R.string.stack_empty));
		}
		else
		{
			sb.append(TextUtils.join(",", stackArrayList));
			
			sb.append("]");
			
			if(full)
			{
				sb.append("\n");
				sb.append(getString(R.string.stack_full));
			}
			
			stackTextView.setText(sb.toString());
		}
	}
	
	private void errorPopUp(String errorMessage)
	{
		AlertDialog builder = new AlertDialog.Builder(instance)
				.setTitle(getString(R.string.error_dialog_pop_up_title))
				.setMessage(errorMessage)
				.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				}).show();
	}
}
