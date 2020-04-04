package com.example.mareu;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.DI.DI;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.meeting.MeetingActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {
    private static int ITEMS_COUNT = 7;
    private MeetingActivity mActivity;
    private MeetingApiService apiService;

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityTestRule = new ActivityTestRule<>(MeetingActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        assertThat(mActivity, notNullValue());

        apiService = DI.getNewInstanceApiService();

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myMeeting_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.activity_meeting_recyclerview_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myMeeting_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.activity_meeting_recyclerview_list)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.activity_meeting_recyclerview_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.activity_meeting_recyclerview_list)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * Check the page AddMeeting launch
     */
//    @Test
//    public void checkAddMeetingActivityLaunch() {
//        Intents.init();
//        // Click on the first item of the list
//        onView(withId(R.id.activity_meeting_add)).perform(click());
//        //We check that the elements of this new activity exist
//        intended(hasComponent(AddMeetingActivity.class.getName()));
//    }

    /** Check filled fields  */


    /** Check the creation of the meeting */



}
