package com.example.android.spaceinvaders;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        glue = "classpath:com.example.android.spaceinvaders",
        features = "classpath:features//Botones.feature"
)
public class CucumberTestCase {
}