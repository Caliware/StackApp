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
	private Boolean test = false;

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
		clearButton.setOnClickListener(this);
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
		if (id == R.id.exit) {
			finish();
			
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
			pushStack(stackInputEditText.getText().toString());
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

	public Boolean validateInput(String input){

		boolean success = false;

		String regex = "^[0-9]{1}$";

//		stackInputEditText.setError(null);

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

	public Boolean pushStack(String input){
		
		boolean success = true;
		if(stackInputEditText != null)
		{
			//String input = stackInputEditText.getText().toString();

			if(validateInput(input))
			{
				if(stackArrayList.size() < 3)
				{
					stackArrayList.add(Integer.valueOf(input));
					if(!test)
					{
						displayStack();
					}
				}
				else
				{
					errorPopUp(getString(R.string.push_error_full_stack));
					if(!test)
					{
						displayStack();
					}
				}
			}
			else
			{
				success = false;
			}
		}
		return success;
	}

	public void popStack(){
		
		if (stackArrayList.size() > 0)
		{
			stackArrayList.remove(stackArrayList.size() - 1);
			
		}
		else
		{
			errorPopUp(getString(R.string.stack_empty));
		}
		
		displayStack();
		
	}

	public void clearStack(){
		
		stackInputEditText.setText(null);
		
		stackArrayList.clear();
		displayStack();
	}
	
	private void displayStack()
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
			
			if(stackArrayList.size() == 3)
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
	
	public int get(int numberInList)
	{
		return stackArrayList.get(numberInList);
	}
	public int getListSize()
	{
		return stackArrayList.size();
	}
	public Boolean isListEmpty()
	{
		if(stackArrayList.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	public void testMethod()
	{
		test = true;
	}
	public void endTestMethod()
	{
		test = false;
	}

}
