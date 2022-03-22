/**
 * @author RK078086
 */
package com.ritesh.practice.ToDoList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ritesh.practice.ToDoList.DBConnectionManager;
import com.ritesh.practice.ToDoList.Note;
import com.ritesh.practice.ToDoList.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class NoteRepositoryTest {

	@InjectMocks
	NoteRepository noteRepository;

	@Mock
    DBConnectionManager DBC;

	@Mock
	public Connection mockConnection;

	@Mock
	public PreparedStatement mockStatement;

	@Mock
	public ResultSet rs;

	@Mock
	public DataSource ds;

	Note note;

	/**
	 * 
	 * @throws java.lang.Exception
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		noteRepository.setDBConnectionManager(DBC);
		when(DBC.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement((String) Mockito.any())).thenReturn(mockStatement);

		note = new Note();
		note.setNoteId(1);
		note.setActiveNote(1);
		note.setCompleteStatus(1);
		note.setNote("Expected");
		note.setPriority(1);
		note.setAddedTime(null);
		note.setNotificationTime(null);
		note.setModified(null);
		note.setDeleted(null);

	}

	/**
	 * Test method for
	 * {@link NoteRepository#getNotes()}.
	 * 
	 * @throws SQLException
	 */

	@Test
	void testGetNotes() throws SQLException {
		List<Note> noteList = new ArrayList<Note>();

		noteList.add(note);

		when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(rs);

		when(rs.first()).thenReturn(true);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(note.getNoteId());
		when(rs.getString(2)).thenReturn("Expected");
		when(rs.getInt(3)).thenReturn(note.getPriority());
		when(rs.getInt(4)).thenReturn(note.getCompleteStatus());
		when(rs.getString(5)).thenReturn(null);
		when(rs.getString(6)).thenReturn(null);
		when(rs.getString(7)).thenReturn(null);
		when(rs.getInt(8)).thenReturn(note.getActiveNote());
		when(rs.getString(9)).thenReturn(null);

		List<Note> noteRs = noteRepository.getNotes();
		assertNotNull(noteRs);
		assertEquals("Expected", noteRs.get(0).getNote());
		assertEquals(1, noteRs.get(0).getNoteId());
		assertEquals(1, noteRs.get(0).getActiveNote());
		assertEquals(1, noteRs.get(0).getCompleteStatus());
		assertEquals(1, noteRs.get(0).getPriority());
		assertEquals(1, noteRs.size());
	}
	
	/**
	 * Test method for
	 * {@link NoteRepository#getCompleteNotes()}.
	 * 
	 * @throws SQLException
	 */

	@Test
	void testgetCompleteNotes() throws SQLException {
		List<Note> noteList = new ArrayList<Note>();

		noteList.add(note);

		when(mockStatement.executeQuery(Mockito.anyString())).thenReturn(rs);

		when(rs.first()).thenReturn(true);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(note.getNoteId());
		when(rs.getString(2)).thenReturn("Expected");
		when(rs.getInt(3)).thenReturn(note.getPriority());
		when(rs.getInt(4)).thenReturn(note.getCompleteStatus());
		when(rs.getString(5)).thenReturn(null);
		when(rs.getString(6)).thenReturn(null);
		when(rs.getString(7)).thenReturn(null);
		when(rs.getInt(8)).thenReturn(note.getActiveNote());
		when(rs.getString(9)).thenReturn(null);

		List<Note> noteRs = noteRepository.getCompleteNotes();
		assertNotNull(noteRs);
		assertEquals("Expected", noteRs.get(0).getNote());
		assertEquals(1, noteRs.get(0).getNoteId());
		assertEquals(1, noteRs.get(0).getActiveNote());
		assertEquals(1, noteRs.get(0).getCompleteStatus());
		assertEquals(1, noteRs.get(0).getPriority());
		assertEquals(1, noteRs.size());
	}

	/**
	 * Test method for
	 * {@link NoteRepository#getNoteById(int)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	void TestgetNoteById() throws SQLException {
		when(mockStatement.executeQuery()).thenReturn(rs);

		when(rs.first()).thenReturn(true);
		when(rs.next()).thenReturn(true).thenReturn(false);
		when(rs.getInt(1)).thenReturn(note.getNoteId());
		when(rs.getString(2)).thenReturn("Expected");
		when(rs.getInt(3)).thenReturn(note.getPriority());
		when(rs.getInt(4)).thenReturn(note.getCompleteStatus());
		when(rs.getString(5)).thenReturn(null);
		when(rs.getString(6)).thenReturn(null);
		when(rs.getString(7)).thenReturn(null);
		when(rs.getInt(8)).thenReturn(note.getActiveNote());
		when(rs.getString(9)).thenReturn(null);

		Note noteResult = noteRepository.getNoteById(1);

		assertNotNull(noteResult);
		assertEquals("Expected", noteResult.getNote());
		assertEquals(1, noteResult.getNoteId());
		assertEquals(1, noteResult.getActiveNote());
		assertEquals(1, noteResult.getCompleteStatus());
		assertEquals(1, noteResult.getPriority());

	}

	/**
	 * Test method for
	 * {@link NoteRepository#ComleteNoteStatus(Note)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	void TestComleteNoteStatus() throws SQLException {
		when(mockStatement.executeUpdate()).thenReturn(1);
		Note n1 = new Note();
		n1.setNoteId(1);
		n1.setCompleteStatus(1);
		n1.setCompleted("2020-05-04 06:20:00");
		boolean status = noteRepository.ComleteNoteStatus(n1);
		assertEquals(true, status);

	}

	/**
	 * Test method for
	 * {@link NoteRepository#updateDeleted(Note)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	void TestupdateDeleted() throws SQLException {
		when(mockStatement.executeUpdate()).thenReturn(1);
		Note n1 = new Note();
		n1.setNoteId(1);
		n1.setActiveNote(1);
		n1.setDeleted("2020-05-04 06:20:00");
		boolean status = noteRepository.updateDeleted(n1);
		assertEquals(true, status);

	}

	/**
	 * Test method for
	 * {@link NoteRepository#addNoted(Note)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	void TestaddNote() throws SQLException {
		when(mockStatement.executeUpdate()).thenReturn(1);
		Note n1 = new Note();
		n1.setActiveNote(1);
		n1.setCompleteStatus(0);
		n1.setNote("Expected");
		n1.setPriority(1);
		n1.setAddedTime("2020-02-28");
		n1.setNotificationTime("2020-03-01");
		n1.setModified(null);
		n1.setDeleted(null);
		boolean status = noteRepository.addNote(n1);
		assertEquals(true, status);

	}

	/**
	 * Test method for
	 * {@link NoteRepository#noteModify(Note)}.
	 * 
	 * @throws SQLException
	 */
	@Test
	void TestnoteModify() throws SQLException {
		when(mockStatement.executeUpdate()).thenReturn(1);
		Note n1 = new Note();
		n1.setNoteId(1);
		n1.setNote("Expected Modification");
		n1.setModified("2020-05-04 06:20:00");
		boolean status = noteRepository.noteModify(n1);
		assertEquals(true, status);

	}

}
