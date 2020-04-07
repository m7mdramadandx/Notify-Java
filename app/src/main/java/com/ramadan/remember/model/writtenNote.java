package com.ramadan.remember.model;

public class writtenNote {
    private String noteID;
    private String noteName;
    private String noteDate;
    private String noteData;
    private int noteColor;
    private int fontColor;

    public writtenNote() {
    }

    public writtenNote(String noteID, String noteDate, String noteName, String noteData, int noteColor, int fontColor) {
        this.noteID = noteID;
        this.noteName = noteName;
        this.noteDate = noteDate;
        this.noteData = noteData;
        this.noteColor = noteColor;
        this.fontColor = fontColor;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteDate() {
        return noteDate;
    }

    public void setNoteDate(String noteDate) {
        this.noteDate = noteDate;
    }

    public String getNoteData() {
        return noteData;
    }

    public void setNoteData(String noteData) {
        this.noteData = noteData;
    }

    public int getNoteColor() {
        return noteColor;
    }

    public void setNoteColor(int noteColor) {
        this.noteColor = noteColor;
    }

    public int getfontColor() {
        return fontColor;
    }

    public void setfontColor(int fontColor) {
        this.fontColor = fontColor;
    }

}
