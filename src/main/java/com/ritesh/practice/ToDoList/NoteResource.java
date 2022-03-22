package com.ritesh.practice.ToDoList;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("notes")
public class NoteResource {

	NoteRepository repo = new NoteRepository();
	DBConnectionManager dbc = new DBConnectionManager();
	

	/**
	 * Resource to print all the Notes stored on data base
	 * 
	 * @return  List of all the note stored on the database
	 * 
	 * @see Noterepository
	 *  
	 */		
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Note> getNotes() {
		repo.setDBConnectionManager(dbc);
		List<Note> noteList;
		noteList=repo.getNotes();
		
		if (noteList.isEmpty())
			throw new NullPointerException();
		return noteList;
	}

	/**
	 * Resource to print note by Id stored on data base
	 * 
	 * @return Note Object  the specific Note with the given Id to resource layer
	 * 
	 * @see Note
	 * @see Noterepository
	 * 
	 * @param id is the id of a Note
	 * 
	 */

	@GET
	@Path("note/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Note getNote(@PathParam("id") int id) {
		repo.setDBConnectionManager(dbc);
		Note note;
		note=repo.getNoteById(id);
		
		if (note == null)
			throw new NullPointerException();
		return note;
	}
	
	/**
	 * Resource to print all the completed Notes stored on data base
	 * 
	 * @return  List of all the completed note stored on the database
	 * 
	 * @see Noterepository
	 *  
	 */		
	
	@GET
	@Path("note/completed")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Note> getCompleteNotes() {
		repo.setDBConnectionManager(dbc);
		List<Note> noteList;
		noteList=repo.getCompleteNotes();
		
		if (noteList.isEmpty())
			throw new NullPointerException();
		return noteList;
	}
	
	/**
	 * Resource to create a new Note
	 * 
	 * @return Note(object)
	 * 
	 * @param note is a  of Note class which is created on being passed 
	 * 
	 * @see Noterepository
	 * 
	 */

	@POST
	@Path("note/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Note addNote(Note note) {
		repo.setDBConnectionManager(dbc);
		boolean success = false;
		success = repo.addNote(note);
		if (success)
			System.out.println("Successfully addd !");
		return note;
	}
	
	/**
	 * Resource to mark a added note complete
	 * 
	 * @return the changes made Note(object)
	 * 
	 * @param note is the object in which the changes are being made.
	 * 			It consist of NoteId,CompleteStatus,Completed date. 
	 * @see Noterepository
	 */

	@PUT
	@Path("note/complete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Note updateStatus(Note note) {
		repo.setDBConnectionManager(dbc);
		boolean success = false;
		success = repo.ComleteNoteStatus(note);

		if (success)
			System.out.println("Successfully Updated !");

		return note;

	}
	/**
	 * Resource to modify the existing note
	 * 
	 * @return The changes made Note(object)
	 * 
	 * @param note is the Note object in which the note has to be updated
	 * 
	 * @see NoteRepository 
	 */

	@PUT
	@Path("note/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	public Note NoteModify(Note note) {
		repo.setDBConnectionManager(dbc);
		boolean success = false;
		success = repo.noteModify(note);

		if (success)
			System.out.println("Successfully Updated !");

		return note;

	}
	/**
	 * Resource to virtually delete a note by making it activenote entity 0
	 * 
	 * @return deleted Note (object)
	 * 
	 * @param note is the note which is to be virtually deleted.
	 * 
	 * @see NoteRepository 
	 */

	@PUT
	@Path("note/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	public Note virtualDelete(Note note) {
		repo.setDBConnectionManager(dbc);
		boolean success = false;
		success = repo.updateDeleted(note);

		if (success)
			System.out.println("Successfully Updated !");

		return note;

	}

}
