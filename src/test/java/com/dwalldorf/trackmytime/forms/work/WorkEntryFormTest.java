package com.dwalldorf.trackmytime.forms.work;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.model.WorkEntry;
import java.util.Date;
import org.joda.time.DateTime;
import org.junit.Test;

public class WorkEntryFormTest extends BaseTest {

    @Test
    public void testToWorkEntry() {
        WorkEntryForm form = new WorkEntryForm()
                .setId("workId")
                .setUserId("userId")
                .setCustomerId("customerId")
                .setProjectId("projectId")
                .setStart(new DateTime().minusHours(2).toDate())
                .setStop(new Date())
                .setComment("comment");
        WorkEntry entry = form.toWorkEntry();

        assertEquals(form.getId(), entry.getId());
        assertEquals(form.getUserId(), entry.getUserId());
        assertEquals(form.getCustomerId(), entry.getCustomerId());
        assertEquals(form.getProjectId(), entry.getProjectId());
        assertEquals(form.getComment(), entry.getComment());

        assertEquals(new DateTime(form.getStart()), entry.getStart());
        assertEquals(new DateTime(form.getStop()), entry.getStop());
    }

    @Test
    public void testToWorkEntry_StartNull() {
        WorkEntryForm form = new WorkEntryForm().setStart(null);
        WorkEntry entry = form.toWorkEntry();

        assertNull(entry.getStart());
    }

    @Test
    public void testToWorkEntry_StopNull() {
        WorkEntryForm form = new WorkEntryForm().setStop(null);
        WorkEntry entry = form.toWorkEntry();

        assertNull(entry.getStop());
    }

    @Test
    public void testFromWorkEntry() {
        WorkEntry entry = new WorkEntry()
                .setId("workId")
                .setUserId("userId")
                .setCustomerId("customerId")
                .setProjectId("projectId")
                .setStart(new DateTime().minusHours(2))
                .setStop(new DateTime())
                .setComment("comment");
        WorkEntryForm form = WorkEntryForm.fromWorkEntry(entry);

        assertEquals(entry.getId(), form.getId());
        assertEquals(entry.getUserId(), form.getUserId());
        assertEquals(entry.getCustomerId(), form.getCustomerId());
        assertEquals(entry.getProjectId(), form.getProjectId());
        assertEquals(entry.getComment(), form.getComment());

        assertEquals(entry.getStart(), new DateTime(form.getStart()));
        assertEquals(entry.getStop(), new DateTime(form.getStop()));
    }

    @Test
    public void testFromWorkEntry_StartNull() {
        WorkEntry entry = new WorkEntry().setStart(null);
        WorkEntryForm form = WorkEntryForm.fromWorkEntry(entry);

        assertNull(form.getStart());
    }

    @Test
    public void testFromWorkEntry_StopNull() {
        WorkEntry entry = new WorkEntry().setStop(null);
        WorkEntryForm form = WorkEntryForm.fromWorkEntry(entry);

        assertNull(form.getStop());
    }

    @Test
    public void testMissingStop_NoStart() throws Exception {
        WorkEntryForm form = new WorkEntryForm();
        assertFalse(form.missingStop());
    }

    @Test
    public void testMissingStop_NoStop() throws Exception {
        WorkEntryForm form = new WorkEntryForm()
                .setStart(new Date());

        assertTrue(form.missingStop());
    }

    @Test
    public void testMissingStop_StartAndStopSet() throws Exception {
        WorkEntryForm form = new WorkEntryForm()
                .setStart(new Date())
                .setStop(new Date());

        assertFalse(form.missingStop());
    }
}