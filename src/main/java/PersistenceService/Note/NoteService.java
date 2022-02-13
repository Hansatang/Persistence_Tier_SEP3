package PersistenceService.Note;

import Model.Note;
import PersistenceService.PersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoteService extends PersistenceService implements INoteService{

    /**
     * Edit note by updating the table notes in the database
     * @param note
     * @return
     */
    @Override
    public ResponseEntity<Void> editNote(String note) {

        Note temp = gson.fromJson(note, Note.class);
        String sqlStatement = "UPDATE notelender.notes SET week = ?, year = ?, name = ?, status = ?, text = ? WHERE id = ?";
        try {
            PreparedStatement editNote = connection.prepareStatement(sqlStatement);
            editNote.setInt(1, temp.getWeek());
            editNote.setInt(2, temp.getYear());
            editNote.setString(3, temp.getName());
            editNote.setString(4, temp.getStatus());
            editNote.setString(5, temp.getText());
            editNote.setInt(6, temp.getId());
            editNote.executeUpdate();

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add note by inserting a new note inside the table of notes in the database
     * @param note
     * @return
     */
    @Override
    public ResponseEntity<Void> addNote(String note) {
        Note temp = gson.fromJson(note, Note.class);
        String sqlStatement = "INSERT INTO notelender.notes (group_id,week,year,name,status,text) " +
                "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement addNote = connection.prepareStatement(sqlStatement);
            addNote.setInt(1, temp.getGroupId());
            addNote.setInt(2, temp.getWeek());
            addNote.setInt(3, temp.getYear());
            addNote.setString(4, temp.getName());
            addNote.setString(5, temp.getStatus());
            addNote.setString(6, temp.getText());
            addNote.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method which takes notes from the list by group id
     * and stores them in NoteList
     * @param id
     * @return Notelist and HttpStatus.OK status, or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<List<Note>> getNoteList(int id) {
        List<Note> NoteList = new ArrayList<>();
        try {
            String sqlStatement = "SELECT * FROM notelender.notes WHERE group_id = ? ORDER BY year ASC, week";
            PreparedStatement getNote = connection.prepareStatement(sqlStatement);
            getNote.setInt(1, id);
            ResultSet rs = getNote.executeQuery();
            while (rs.next()) {
                Note noteToAdd = new Note(rs.getInt(1),
                        rs.getInt(2), rs.getInt(3),
                        rs.getInt(4), rs.getString(5),
                        rs.getString(6), rs.getString(7));
                NoteList.add(noteToAdd);
            }
            return new ResponseEntity<>(NoteList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Removes note from notes table in database using id
     * @param noteId
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> deleteNote(int noteId) {
        String sqlStatement = "DELETE FROM notelender.notes WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, noteId);
            statement.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
