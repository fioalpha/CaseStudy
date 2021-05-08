package com.fioalpha.poc.form.presentation.calcule

import android.widget.EditText
import androidx.test.rule.ActivityTestRule
import com.fioalpha.poc.form.R
import com.schibsted.spain.barista.assertion.BaristaAssertions.assertAny
import com.schibsted.spain.barista.assertion.BaristaAssistiveTextAssertions.assertAssistiveText
import com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertErrorDisplayed
import com.schibsted.spain.barista.assertion.BaristaErrorAssertions.assertNoErrorDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertContains
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import com.schibsted.spain.barista.internal.viewaction.SleepViewAction.sleep
import kotlinx.coroutines.delay
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
        assertErrorDisplayed(R.id.edtDate, R.string.erro_fields)
    }

    @Test
    fun whenClickButtonDateFieldsFailed() {
        activityRules.launchActivity(null)

        writeTo(R.id.edtInvestedAmount, "1000")
        writeTo(R.id.edtDate, "23/10/20")
        writeTo(R.id.edtRate, "100")
        clickOn(R.id.button)

        assertNoErrorDisplayed(R.id.edtInvestedAmount)
        assertErrorDisplayed(R.id.edtDate, R.string.erro_fields)
        sleep(2000)
        assertNoErrorDisplayed(R.id.edtRate)

    }

    @Test
    fun whenFillEditText() {
        activityRules.launchActivity(null)
        writeTo(R.id.edtInvestedAmount, "1000")
        writeTo(R.id.edtDate, "23/10/2011")
        writeTo(R.id.edtRate, "100")

        assertContains(R.id.edtInvestedAmount, "R\$ 10,00")
        assertContains(R.id.edtDate, "23/10/2011")
        assertDisplayed(R.id.edtRate, "100%")
        clickOn(R.id.button)
        assertDisplayed(R.id.progressBar)

    }
}