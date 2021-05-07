package com.fioalpha.poc.form.presentation.calcule

import androidx.test.rule.ActivityTestRule
import com.fioalpha.poc.form.R
import com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertErrorDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Rule
import org.junit.Test

class InvestedActivityTest {

    @get:Rule
    val activityRules = ActivityTestRule(InvestedActivity::class.java, true, false)


    @Test
    fun whenStartingApplication()  {
        activityRules.launchActivity(null)
        assertDisplayed(R.id.edtDate)
    }

    @Test
    fun whenClickButtonAllFieldsClean() {
        activityRules.launchActivity(null)
        clickOn(R.id.button)
        assertErrorDisplayed(R.id.edtInvestedAmount, R.string.erro_fields)
        assertErrorDisplayed(R.id.edtRate, R.string.erro_fields)
    }

    @Test
    fun whenFillEditText() {
        activityRules.launchActivity(null)
        writeTo(R.id.edtInvestedAmount, "100")
        writeTo(R.id.edtRate, "100")
        writeTo(R.id.edtDate, "23/10/2010")
        clickOn(R.id.button)
        assertDisplayed(R.id.progressBar)
    }
}