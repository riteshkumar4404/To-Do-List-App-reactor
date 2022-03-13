package com.cerner.DevCenter.ToDoList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 * This is the implementation class DAO 
 */

public class NoteRepository {

	private DBConnectionManager dbc;
	private Connection conn;

	public void setDBConnectionManager(DBConnectionManager db) {
		this.dbc = db;
	}

	public void setConnection(Connection con) {
		this.conn = con;
	}

	/**
	 * This the implementation for fetching all the Notes
	 * 
	 * @returns all the list of note stored on the database
	 * 
	 */

	public List<Note> getNotes() {
		List<Note> listNotes = new ArrayList<Note>();
		String sql = "select * from todo where ActiveNote=1 and CompleteStatus=0";

		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				Note n = new Note();
				n.setNoteId(rs.getInt(1));
				n.setNote(rs.getString(2));
				n.setPriority(rs.getInt(3));
				n.setCompleteStatus(rs.getInt(4));
				n.setAddedTime(rs.getString(5));
				n.setNotificationTime(rs.getString(6));
				n.setModified(rs.getString(7));
				n.setActiveNote(rs.getInt(8));
				n.setDeleted(rs.getString(9));
				n.setCompleted(rs.getString(10));

				listNotes.add(n);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNotes;
	}
	
	/**
	 * This the implementation for fetching all the completed Notes
	 * 
	 * @returns all the list of note stored on the database with complete status
	 * 
	 */

	public List<Note> getCompleteNotes() {
		List<Note> listNotes = new ArrayList<Note>();
		String sql = "select * from todo where CompleteStatus=1";

		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				Note n = new Note();
				n.setNoteId(rs.getInt(1));
				n.setNote(rs.getString(2));
				n.setPriority(rs.getInt(3));
				n.setCompleteStatus(rs.getInt(4));
				n.setAddedTime(rs.getString(5));
				n.setNotificationTime(rs.getString(6));
				n.setModified(rs.getString(7));
				n.setActiveNote(rs.getInt(8));
				n.setDeleted(rs.getString(9));
				n.setCompleted(rs.getString(10));

				listNotes.add(n);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listNotes;
	}

	/**
	 * This the implementation to fetch a single note from the DataBase
	 * 
	 * @returns only single notes from the database with the specific id
	 * 
	 * @param id is the note id which is to be displayed.
	 * 
	 */
	public Note getNoteById(int id) {
		String sql = "select * from Todo where Noteid = ?";

		Note n = new Note();

		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs != null && rs.next()) {

				n.setNoteId(rs.getInt(1));
				n.setNote(rs.getString(2));
				n.setPriority(rs.getInt(3));
				n.setCompleteStatus(rs.getInt(4));
				n.setAddedTime(rs.getString(5));
				n.setNotificationTime(rs.getString(6));
				n.setModified(rs.getString(7));
				n.setActiveNote(rs.getInt(8));
				n.setDeleted(rs.getString(9));
				n.setCompleted(rs.getString(10));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;

	}
	
	/**
	 * This is implementation part to mark a note complete
	 * 
	 * @return boolean (based on the changes made to the database)
	 *
	 * @param n1 is the Note object which contains Id, CompleteStatus,Complete Date.
	 * 			 This note has to be marked as completed.
	 */

	public boolean ComleteNoteStatus(Note n1) {
		int row = 0;
		String sql = "UPDATE Todo SET CompleteStatus = ?,Completed = ? WHERE Noteid = ?";
		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setInt(1, n1.getCompleteStatus());
			ps.setString(2, n1.getCompleted());
			ps.setInt(3, n1.getNoteId());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0;

	}
	
	/**	
	 * This a implementation class to virtually delete a node 
	 * 
	 * @return type: Boolean(one basis of execution of task)
	 *  
	 * @param n1 is Note object which has to be virtually deleted
	 */

	public boolean updateDeleted(Note n1) {
		int row = 0;

		String sql = "UPDATE Todo SET ActiveNote = ?,Deleted = ? WHERE Noteid = ?";
		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setInt(1, n1.getActiveNote());
			ps.setString(2, n1.getDeleted());
			ps.setInt(3, n1.getNoteId());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0;

	}
	
	/**
	 * This is a implementation to add a new note to the database
	 * 
	 * @return Boolean (on the basis of execution of task)
	 * 
	 * @param n is the note that has to be added to the database
	 * 
	 */

	public boolean addNote(Note n) {
		int row = 0;
		String sql = "INSERT INTO Todo (Note, Priority, CompleteStatus, Added, Notification, ActiveNote) VALUES (?,?,?,?,?,?)";
		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, n.getNote());
			ps.setInt(2, n.getPriority());
			ps.setInt(3, n.getCompleteStatus());
			ps.setString(4, n.getAddedTime());
			ps.setString(5, n.getNotificationTime());
			ps.setInt(6, n.getActiveNote());

			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0;
	}

	/**
	 * This is a implementation class to modify a existing note on to the database
	 * 
	 * @return Boolean (on the basis of execution of task)
	 * 
	 * @param n1 is the Note object that contains note Id, updated Note, and modified date.
	 * 			this is used to modify the existing data 
	 */
	
	public boolean noteModify(Note n1) {
		int row = 0;

		String sql = "UPDATE todo SET Note = ?, Priority = ?, Notification = ?, Modified = ? WHERE (Noteid = ?)";
		try {
			this.setConnection(this.dbc.getConnection());
			PreparedStatement ps = this.conn.prepareStatement(sql);
			ps.setString(1, n1.getNote());
			ps.setInt(2, n1.getPriority());
			ps.setString(3, n1.getNotificationTime());
			ps.setString(4, n1.getModified());
			ps.setInt(5, n1.getNoteId());
			row = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row > 0;
	}

}
