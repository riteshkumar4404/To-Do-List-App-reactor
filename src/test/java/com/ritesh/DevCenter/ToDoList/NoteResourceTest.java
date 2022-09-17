/**
 * @author RK078086
 */
package com.ritesh.practice.ToDoList;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

import com.ritesh.practice.ToDoList.Note;
import com.ritesh.practice.ToDoList.NoteRepository;
import com.ritesh.practice.ToDoList.NoteResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class NoteResourceTest {
	@InjectMocks
	NoteResource noteResource;

	@Mock
    NoteRepository noteRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for
	 * {@link NoteResource#getNotes()}.
	 */
	@Test
	void testGetNotes() {
		List<Note> noteList = new ArrayList<Note>();
		Note note = new Note();
		note.setNoteId(1);
		note.setActiveNote(1);
		note.setCompleteStatus(0);
		note.setNote("Expected");
		note.setPriority(1);
		note.setAddedTime(null);
		note.setNotificationTime(null);
		note.setModified(null);
		note.setDeleted(null);
		noteList.add(note);
		when(noteRepository.getNotes()).thenReturn(noteList);
		List<Note> noteRs = noteResource.getNotes();
		assertNotNull(noteRs);
		assertEquals(true, noteRs.contains(note));
	}
	
	/**
	 * Test method for
	 * {@link NoteResource#getCompleteNotes()}.
	 */

	@Test
	void testgetCompleteNotes() {
		List<Note> noteList = new ArrayList<Note>();
		Note note = new Note();
		note.setNoteId(1);
		note.setActiveNote(1);
		note.setCompleteStatus(1);
		note.setNote("Expected");
		note.setPriority(1);
		note.setAddedTime(null);
		note.setNotificationTime(null);
		note.setModified(null);
		note.setDeleted(null);
		noteList.add(note);
		when(noteRepository.getCompleteNotes()).thenReturn(noteList);
		List<Note> noteRs = noteResource.getCompleteNotes();
		assertNotNull(noteRs);
		assertEquals(true, noteRs.contains(note));
	}

	/**
	 * Test method for {@link NoteResource#getNotes()
	 * for null pointer exception}.
	 */

	@Test
	void testGetNotes_NullPointerException() {
		when(noteRepository.getNotes()).thenReturn(null);

		assertThrows(NullPointerException.class, new Executable() {
			public void execute() throws Throwable {
				noteResource.getNotes();
			}
		}

		);
	}

	/**
	 * Test method for
	 * {@link NoteResource#getNote(int)}.
	 * 
	 * @throws Exception
	 */
	@Test
	void testGetNote() throws Exception {
		Note note = new Note();
		note.setNoteId(1);
		note.setActiveNote(1);
		note.setCompleteStatus(1);
		note.setNote("Expected");
		note.setPriority(1);
		note.setAddedTime(null);
		note.setNotificationTime(null);
		note.setModified(null);
		note.setDeleted(null);

		when(noteRepository.getNoteById(anyInt())).thenReturn(note);
		Note noteRs = noteResource.getNote(1);
		assertNotNull(noteRs);
		assertEquals("Expected", noteRs.getNote());
		assertEquals(1, noteRs.getNoteId());
		assertEquals(1, noteRs.getActiveNote());
		assertEquals(1, noteRs.getCompleteStatus());
		assertEquals(1, noteRs.getPriority());
	}

	/**
	 * Test method for
	 * {@link NoteResource#getNote(int) for null
	 * pointer exception}.
	 * 
	 * @throws Exception
	 */

	@Test
	void testGetNote_NoteNotfoundException() throws Exception {
		when(noteRepository.getNoteById(anyInt())).thenReturn(null);

		assertThrows(NullPointerException.class, new Executable() {
			public void execute() throws Throwable {
				noteResource.getNote(3);
			}
		}

		);
	}

	/**
	 * Test method for
	 * {@link NoteResource#AddNote(Note)}.
	 * 
	 */

	@Test
	void testAddNote() {

		Note note = new Note();
		note.setActiveNote(1);
		note.setCompleteStatus(0);
		note.setNote("Expected");
		note.setPriority(1);
		note.setAddedTime("2020-02-28");
		note.setNotificationTime("2020-03-01");
		note.setModified(null);
		note.setDeleted(null);

		when(noteRepository.addNote(Mockito.any())).thenReturn(true);

		Note noteadd = noteResource.addNote(note);
		boolean notestatus = noteRepository.addNote(note);

		assertNotNull(noteadd);
		assertEquals("Expected", noteadd.getNote());
		assertEquals(1, noteadd.getActiveNote());
		assertEquals(0, noteadd.getCompleteStatus());
		assertEquals(1, noteadd.getPriority());
		assertEquals(true, notestatus);

	}

	/**
	 * Test method for
	 * {@link NoteResource#UpdateStatus(Note)}.
	 * 
	 */

	@Test
	void TestUpdateStatus() {

		Note note = new Note();
		note.setNoteId(1);
		note.setCompleteStatus(1);
		note.setCompleted("2020-05-04 06:20:00");

		when(noteRepository.ComleteNoteStatus(Mockito.any())).thenReturn(true);

		Note noteupdate = noteResource.updateStatus(note);
		boolean notestatus = noteRepository.ComleteNoteStatus(note);

		assertNotNull(noteupdate);
		assertEquals(1, noteupdate.getNoteId());
		assertEquals(1, noteupdate.getCompleteStatus());
		assertEquals("2020-05-04 06:20:00", noteupdate.getCompleted());
		assertEquals(true, notestatus);

	}

	/**
	 * Test method for
	 * {@link NoteResource#NoteModify(Note)}.
	 * 
	 */

	@Test
	void TestNoteModify() {

		Note note = new Note();
		note.setNoteId(1);
		note.setNote("Expected Modification");
		note.setModified("2020-05-04 06:20:00");

		when(noteRepository.noteModify(Mockito.any())).thenReturn(true);

		Note noteModified = noteResource.NoteModify(note);
		boolean notestatus = noteRepository.noteModify(note);

		assertNotNull(noteModified);
		assertEquals(1, noteModified.getNoteId());
		assertEquals("Expected Modification", noteModified.getNote());
		assertEquals("2020-05-04 06:20:00", noteModified.getModified());
		assertEquals(true, notestatus);

	}

	/**
	 * Test method for
	 * {@link NoteResource#VirtualDelete(Note)}.
	 * 
	 */

	@Test
	void TestVirtualDelete() {
		Note note = new Note();
		note.setNoteId(1);
		note.setActiveNote(0);
		note.setDeleted("2020-05-04 06:20:00");

		when(noteRepository.updateDeleted(Mockito.any())).thenReturn(true);

		Note noteDeleted = noteResource.virtualDelete(note);
		boolean notestatus = noteRepository.updateDeleted(note);

		assertNotNull(noteDeleted);
		assertEquals(1, noteDeleted.getNoteId());
		assertEquals(0, noteDeleted.getActiveNote());
		assertEquals("2020-05-04 06:20:00", noteDeleted.getDeleted());
		assertEquals(true, notestatus);
	}

}
