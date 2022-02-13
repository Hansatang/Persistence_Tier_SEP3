package PersistenceService.Note;

import Model.Note;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface INoteService {

    ResponseEntity<List<Note>> getNoteList(int groupId);

    ResponseEntity<Void> addNote(String json);

    ResponseEntity<Void> editNote(String json);

    ResponseEntity<Void> deleteNote(int noteId);


}
