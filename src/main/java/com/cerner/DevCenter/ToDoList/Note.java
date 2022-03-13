package com.cerner.DevCenter.ToDoList;
/*
 * @This is a POJO 
 */
public class Note {
	private int NoteId;
	private String note;
	private int priority;
	private int CompleteStatus;
	private String addedTime;
	private String NotificationTime;
	private String Modified;
	private int ActiveNote;
	private String Deleted;
	private String Completed;

	public int getNoteId() {
		return NoteId;
	}

	public int getActiveNote() {
		return ActiveNote;
	}

	public void setActiveNote(int activeNote) {
		ActiveNote = activeNote;
	}

	public void setNoteId(int noteId) {
		this.NoteId = noteId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getCompleteStatus() {
		return CompleteStatus;
	}

	public void setCompleteStatus(int CompleteStatu) {
		this.CompleteStatus = CompleteStatu;
	}

	public String getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(String timestamp) {
		this.addedTime = timestamp;
	}

	public String getNotificationTime() {
		return NotificationTime;
	}

	public void setNotificationTime(String notificationTime) {
		NotificationTime = notificationTime;
	}

	public String getModified() {
		return Modified;
	}

	public void setModified(String modified) {
		Modified = modified;
	}

	public String getDeleted() {
		return Deleted;
	}

	public void setDeleted(String deleted) {
		Deleted = deleted;
	}

	public String getCompleted() {
		return Completed;
	}

	public void setCompleted(String completed) {
		Completed = completed;
	}

	@Override
	public String toString() {
		return "Note [NoteId=" + NoteId + ", note=" + note + ", priority=" + priority + ", CompleteStatus="
				+ CompleteStatus + ", addedTime=" + addedTime + ", NotificationTime=" + NotificationTime + ", Modified="
				+ Modified + ", ActiveNote=" + ActiveNote + ", Deleted=" + Deleted + ",Completed=" + Completed + "]";
	}

}
