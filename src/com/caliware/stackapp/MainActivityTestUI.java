package com.caliware.stackapp;

import static org.junit.Assert.*;

import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivityTestUI extends ActivityInstrumentationTestCase2<MainActivity>{

    private MainActivity MainActivity;
    private TextView FirstTestText;
    private Button pushButtonTest;
    private Button popButtonTest;
    private Button clearButtonTest;
    private TextView InfoTextView;

	
	public MainActivityTestUI(Class<MainActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}
	/*
	 * (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 * sets up the test environment
	 */
	protected void setUp() throws Exception {
        super.setUp();
        MainActivity = getActivity();
        
        FirstTestText = (TextView) MainActivity.findViewById(R.id.activity_main_stack_textview);
        setActivityInitialTouchMode(true);
        /*pushButtonTest = (Button) MainActivity.findViewById(R.id.activity_main_push_button);
        popButtonTest = (Button) MainActivity.findViewById(R.id.activity_main_pop_button);
        clearButtonTest = (Button) MainActivity.findViewById(R.id.activity_main_clear_button);*/

    }	
    
    public void testPreconditions() {
		assertNotNull("Activity is null", MainActivity);
		assertNotNull("Text is null", FirstTestText);
	}
	/*
	 * Verify the button layout parameters
	 */
	@MediumTest
	public void testPushClickButton_layout() {
	    final View decorView = MainActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, pushButtonTest);

	    final ViewGroup.LayoutParams layoutParams = pushButtonTest.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}

	@MediumTest
	public void testPopClickButton_layout() {
	    final View decorView = MainActivity.getWindow().getDecorView();

	    ViewAsserts.assertOnScreen(decorView, popButtonTest);

	    final ViewGroup.LayoutParams layoutParams = popButtonTest.getLayoutParams();
	    assertNotNull(layoutParams);
	    assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
	    assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
	}
	/*
	 * test the textView Parameters
	 */
	@MediumTest
	public void testInfoTextView_layout() {
	    final View decorView = MainActivity.getWindow().getDecorView();
	    ViewAsserts.assertOnScreen(decorView, InfoTextView);
	    assertTrue(View.GONE == InfoTextView.getVisibility());
	}
	/*
	 * test text view for push button
	 */
	@MediumTest
	public void testClickPushButton_clickButtonAndExpectInfoText() {
	    String expectedInfoText = MainActivity.getString(R.string.action_stack_push);
	    TouchUtils.clickView(this, pushButtonTest);
	    assertTrue(View.VISIBLE == InfoTextView.getVisibility());
	    assertEquals(expectedInfoText, InfoTextView.getText());
	}
	
	
	/*
	 * tests text view for pop button
	 */
	@MediumTest
	public void testClickPopButton_clickButtonAndExpectInfoText() {
	    String expectedInfoText = MainActivity.getString(R.string.action_stack_pop);
	    TouchUtils.clickView(this, popButtonTest);
	    assertTrue(View.VISIBLE == InfoTextView.getVisibility());
	    assertEquals(expectedInfoText, InfoTextView.getText());
	}
	/*
	 * tests text view for clear button
	 */
	@MediumTest
	public void testClickClearButton_clickButtonAndExpectInfoText() {
	    String expectedInfoText = MainActivity.getString(R.string.action_stack_clear);
	    TouchUtils.clickView(this, clearButtonTest);
	    assertTrue(View.VISIBLE == InfoTextView.getVisibility());
	    assertEquals(expectedInfoText, InfoTextView.getText());
	}
	
	/*
	 * test the validateInput method
	 */
	@Test
	public void testvalidateInput()
	{
		MainActivity = new MainActivity();
		assertFalse(MainActivity.validateInput("-1"));
		assertFalse(MainActivity.validateInput("10")); 
		assertFalse(MainActivity.validateInput("nothing"));
		assertFalse(MainActivity.validateInput(""));
	}
	/*
	 * test the pushStack method
	 */
	@Test
	public void testpushStack()
	{
		MainActivity = new MainActivity();
		MainActivity.pushStack("1");
		MainActivity.pushStack("2");
		MainActivity.pushStack("3");
		assertEquals(1, MainActivity.get(0));
		assertEquals(2, MainActivity.get(1));
		assertEquals(3, MainActivity.get(2));
		assertNotEquals(3, MainActivity.get(0));
		assertNotEquals(2, MainActivity.get(2));
		
	}

}
