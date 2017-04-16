package com.example.android.spaceinvaders;

import android.test.AndroidTestCase;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = {"com.example.android.spaceinvaders.steps"},
        dryRun = false,
        features = {"src/test/resources/features"},
        strict = false
)
public class CucumberTestCase {
}