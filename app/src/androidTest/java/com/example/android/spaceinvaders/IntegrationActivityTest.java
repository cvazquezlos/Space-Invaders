package com.example.android.spaceinvaders;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IntegrationActivityTest {

    public ActivityTestRule<MainActivity> mActivityRule = new
            ActivityTestRule<>(MainActivity.class, false);

    @Before
    public void before_test() {
        Intent startIntent = new Intent();
        mActivityRule.launchActivity(startIntent);
    }

    @Test
    public void test1_mainActivity() {
        onView(withId(R.id.opcion_boton)).perform(click());
    }

    @Test
    public void test2_gameActivity() throws Throwable {
        onView(withId(R.id.play_boton)).perform(click());
        onView(withId(R.id.control_derecha)).perform(click());
        onView(withId(R.id.control_izquierda)).perform(click());
        onView(withId(R.id.disparo)).perform(click());
    }
}


