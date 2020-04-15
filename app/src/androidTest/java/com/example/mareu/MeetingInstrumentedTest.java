package com.example.mareu;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.DI.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.ui.meeting.AddMeetingActivity;
import com.example.mareu.ui.meeting.MeetingActivity;
import com.example.mareu.utils.DeleteViewAction;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.not;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingInstrumentedTest {
    private static int ITEMS_COUNT = 7;
    private MeetingActivity mActivity;
    private MeetingApiService service;

    @Rule
    public ActivityTestRule<MeetingActivity> mActivityTestRule = new ActivityTestRule<>(MeetingActivity.class);

    @Before
    public void setUp() {

        mActivity = mActivityTestRule.getActivity();
        service = DI.getMeetingApiService();
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
        onView(withId(R.id.activity_meeting_recyclerview_list)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * Check the page AddMeeting launch
     */
    @Test
    public void checkAddMeetingActivityLaunch() {
        Intents.init();
        onView(withId(R.id.activity_meeting_add)).perform(click());
        //We check that the elements of this new activity exist
        intended(hasComponent(AddMeetingActivity.class.getName()));
    }

    /**
     * Check filled fields
     */
    @Test
    public void checkFilledFields() {
        onView(withId(R.id.activity_meeting_add)).perform(click());
        onView(withId(R.id.activity_meeting_add_btnvalidate)).perform(click());
        onView(withText("Veuillez renseigner les participants")).inRoot(withDecorView(not(mActivity.getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    /**
     * Check the creation of the meeting
     */
    @Test
    public void checkAllTheMeetings() {
        onView(ViewMatchers.withId(R.id.activity_meeting_recyclerview_list))
                .check(matches(hasChildCount(7)));
    }


    /**
     * Check the filter of the room
     */
    @Test
    public void checkFilterRoom() {
        String room = mActivity.getString(R.string.l);
        List<Meeting> meetings = service.getRoomFilter(room);
        onView(withId(R.id.main_menu_filter)).perform(click());
        onView(withText(R.string.room)).perform(click());
        onView(withText(room)).perform(click());
        onView(ViewMatchers.withId(R.id.activity_meeting_recyclerview_list))
                .check(matches(hasChildCount(meetings.size())));
    }

    /**
     * Check the filter of the date
     * */
    @Test
    public void checkFilterDate() throws InterruptedException {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        List<Meeting> meetings = service.getMeetingsByDate(date);
        onView(withId(R.id.main_menu_filter)).perform(click());
        onView(withText(R.string.date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020, 4,15));
        onView(withId(android.R.id.button1)).perform(click());

        onView(ViewMatchers.withId(R.id.activity_meeting_recyclerview_list))
              .check(matches(hasChildCount(meetings.size())));

    }


    /**
     * Check add meeting
     * */
    @Test
    public void checkAddMeeting() {
        String mail ="aaa@lamzone.com";
        String subject ="reunion S";
        String timeStart ="12H30";
        String timeEnd ="13H00";
        String date ="16/04/2020";

        Intents.init();
        onView(withId(R.id.activity_meeting_add)).perform(click());
        intended(hasComponent(AddMeetingActivity.class.getName()));

        onView(withId(R.id.activity_meeting_add_edtmail)).perform(replaceText(mail));
        onView(withId(R.id.activity_meeting_add_edtsubject)).perform(replaceText(subject));
        onView(withId(R.id.activity_meeting_add_spinnerromm)).perform(click());
        onView(withText(R.string.l)).perform(click());
        onView(withId(R.id.activity_meeting_add_day)).perform(replaceText(date));
        onView(withId(R.id.activity_meeting_add_btntimestart)).perform(replaceText(timeStart));
        onView(withId(R.id.activity_meeting_add_btntimeend)).perform(replaceText(timeEnd));

        onView(withId(R.id.activity_meeting_add_btnvalidate)).perform(click());
        onView(ViewMatchers.withId(R.id.activity_meeting_recyclerview_list))
                .check(withItemCount(ITEMS_COUNT + 1));

    }


}
