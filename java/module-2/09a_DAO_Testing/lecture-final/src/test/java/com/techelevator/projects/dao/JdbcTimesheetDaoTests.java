package com.techelevator.projects.dao;

import com.techelevator.projects.model.Timesheet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JdbcTimesheetDaoTests extends BaseDaoTests {

    private static final Timesheet TIMESHEET_1 = new Timesheet(1, 1, 1,
            LocalDate.parse("2021-01-01"), 1.0, true, "Timesheet 1");
    private static final Timesheet TIMESHEET_2 = new Timesheet(2, 1, 1,
            LocalDate.parse("2021-01-02"), 1.5, true, "Timesheet 2");
    private static final Timesheet TIMESHEET_3 = new Timesheet(3, 2, 1,
            LocalDate.parse("2021-01-01"), 0.25, true, "Timesheet 3");
    private static final Timesheet TIMESHEET_4 = new Timesheet(4, 2, 2,
            LocalDate.parse("2021-02-01"), 2.0, false, "Timesheet 4");

    private JdbcTimesheetDao dao;
    private  Timesheet testTimesheet;

    @Before
    public void setup() {
        dao = new JdbcTimesheetDao(dataSource);
        testTimesheet = new Timesheet(5,2,1, LocalDate.now(), 9.9, true, "Test Timesheet");
    }

    @Test
    public void getTimesheetById_with_valid_id_returns_correct_timesheet() {
        Timesheet timesheet = dao.getTimesheetById(1);
        Assert.assertNotNull("Get timesheet By ID returned a null timesheet", timesheet);
        assertTimesheetsMatch("Get timesheet By ID the incorrect timesheet", TIMESHEET_1, timesheet );

        timesheet = dao.getTimesheetById(4);
        Assert.assertNotNull("Get timesheet By ID returned a null timesheet", timesheet);
        assertTimesheetsMatch("Get timesheet By ID the incorrect timesheet", TIMESHEET_4, timesheet );


    }

    @Test
    public void getTimesheetById_with_invalid_id_returns_null_timesheet() {
        Timesheet timesheet = dao.getTimesheetById(0);
        Assert.assertNull("Get Timesheet with invalid id returns a timesheet instead of a null", timesheet);
    }

    @Test
    public void getTimesheetsByEmployeeId_with_valid_employee_id_returns_list_of_timesheets_for_employee() {
        List<Timesheet> timesheets = dao.getTimesheetsByEmployeeId(1);
        Assert.assertTrue("Get timesheets with vvalid Employee ID is returning no timesheets", timesheets.size() > 0);
        Assert.assertTrue("Get timesheets with vvalid Employee ID is returning an incorrect number of timesheets", timesheets.size() == 2);
        assertTimesheetsMatch("Get timeshets with valid Employee ID -- Timesheet 1 does not match test criteria", TIMESHEET_1, timesheets.get(0));
        assertTimesheetsMatch("Get timeshets with valid Employee ID -- Timesheet 1 does not match test criteria", TIMESHEET_2, timesheets.get(1));

        timesheets = dao.getTimesheetsByEmployeeId(2);
        Assert.assertTrue("Get timesheets with vvalid Employee ID is returning no timesheets", timesheets.size() > 0);
        Assert.assertTrue("Get timesheets with vvalid Employee ID is returning an incorrect number of timesheets", timesheets.size() == 2);
        assertTimesheetsMatch("Get timeshets with valid Employee ID -- Timesheet 1 does not match test criteria", TIMESHEET_3, timesheets.get(0));
        assertTimesheetsMatch("Get timeshets with valid Employee ID -- Timesheet 1 does not match test criteria", TIMESHEET_4, timesheets.get(1));

    }

    @Test
    public void getTimesheetsByProjectId_with_valid_id_returns_list_of_all_timesheets_for_project() {
        List<Timesheet> timesheets = dao.getTimesheetsByProjectId(1);
        Assert.assertTrue("Get timesheets with vvalid Project ID is returning no timesheets", timesheets.size() > 0);
        Assert.assertTrue("Get timesheets with vvalid Project ID is returning an incorrect number of timesheets", timesheets.size() == 3);
        assertTimesheetsMatch("Get timeshets with valid Project ID -- Timesheet 1 does not match test criteria", TIMESHEET_1, timesheets.get(0));
        assertTimesheetsMatch("Get timeshets with valid Project ID -- Timesheet 1 does not match test criteria", TIMESHEET_2, timesheets.get(1));
        assertTimesheetsMatch("Get timeshets with valid Project ID -- Timesheet 1 does not match test criteria", TIMESHEET_3, timesheets.get(2));

        timesheets = dao.getTimesheetsByProjectId(2);
        Assert.assertTrue("Get timesheets with vvalid Project ID is returning no timesheets", timesheets.size() > 0);
        Assert.assertTrue("Get timesheets with vvalid Project ID is returning an incorrect number of timesheets", timesheets.size() == 1);
        assertTimesheetsMatch("Get timeshets with valid Project ID -- Timesheet 1 does not match test criteria", TIMESHEET_4, timesheets.get(0));
    }

    @Test
    public void createTimesheet_creates_timesheet() {
        Timesheet createdTimesheet = dao.createTimesheet(testTimesheet);
        Assert.assertNotNull("Created Time Sheet returned Null Timesheet from Database", createdTimesheet);
        assertTimesheetsMatch("Created Timesheeet returned incorrect/incomplete timesheet", createdTimesheet, testTimesheet);

        Timesheet retrtievedTimeSheet = dao.getTimesheetById(createdTimesheet.getTimesheetId());
        assertTimesheetsMatch("Created Timesheet doesn't match retrieved timesheet by the same id", retrtievedTimeSheet, createdTimesheet);

    }

    @Test
    public void updateTimesheet_updates_timesheet() {
        Timesheet timesheet = dao.getTimesheetById(1);
        timesheet.setEmployeeId(2);
        timesheet.setProjectId(2);
        timesheet.setDateWorked(LocalDate.now());
        timesheet.setHoursWorked(9.9);
        timesheet.setBillable(false);
        timesheet.setDescription("Test");

        Timesheet updatedTimesheet = dao.updateTimesheet(timesheet);
        Assert.assertNotNull("Updated Time Sheet returned Null Timesheet from Database", updatedTimesheet);
        assertTimesheetsMatch("Updated Timesheet doesn't match retrieved timesheet by the same id", updatedTimesheet, timesheet);
    }

    @Test
    public void deleteTimesheetById_deletes_timesheet() {
        int rowsAffected = dao.deleteTimesheetById(1);
        Assert.assertEquals("Delete Timesheet By ID deleted an incorerect number of rows", 1, rowsAffected);

        Timesheet timesheet = dao.getTimesheetById(1);
        Assert.assertNull("Deleted Timesheet was retrieved from the DB. That's bad", timesheet);
    }

    @Test
    public void getBillableHours_returns_correct_total() {
        double total = dao.getBillableHours(1,1);
        Assert.assertEquals("Get Billable Hours returns the incorrect total for mulitple timehseets", 2.5, total, 0.001);

        total = dao.getBillableHours(2,1);
        Assert.assertEquals("Get Billable Hours returns the incorrect total for mulitple timehseets", .25, total, 0.001);

    }

    private void assertTimesheetsMatch(String message, Timesheet expected, Timesheet actual) {
        Assert.assertEquals(message, expected.getTimesheetId(), actual.getTimesheetId());
        Assert.assertEquals(message, expected.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(message, expected.getProjectId(), actual.getProjectId());
        Assert.assertEquals(message, expected.getDateWorked(), actual.getDateWorked());
        Assert.assertEquals(message, expected.getHoursWorked(), actual.getHoursWorked(), 0.001);
        Assert.assertEquals(message, expected.isBillable(), actual.isBillable());
        Assert.assertEquals(message, expected.getDescription(), actual.getDescription());
    }

}
