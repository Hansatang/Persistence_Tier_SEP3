package PersistenceServer;


import Model.Note;
import PersistenceService.Note.INoteService;
import PersistenceService.Note.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    /**
     * Instance variable
     */
    private final INoteService noteService;

    /**
     * Constructor for PersistenceServerController
     */
    public NoteController() {
        this.noteService = new NoteService();
    }

    /**
     * Get note list
     * @param groupId
     * @return persistenceService.getNoteList(groupId)
     */
    @GetMapping("/notes/{groupId}")
    public ResponseEntity<List<Note>> getNoteList(@PathVariable(value = "groupId") int groupId) {
        return noteService.getNoteList(groupId);
    }

    /**
     * Add note
     * @param requestBody
     * @return persistenceService.addNote(requestBody)
     */
    @PostMapping("/note")
    public ResponseEntity<Void> addNote(@RequestBody String requestBody) {
        return noteService.addNote(requestBody);
    }

    /**
     * Edit note
     * @param requestBody
     * @return persistenceService.editNote(requestBody)
     */
    @PutMapping("/note")
    public ResponseEntity<Void> editNote(@RequestBody String requestBody) {
        return noteService.editNote(requestBody);
    }

    /**
     * Delete note
     * @param noteId
     * @return persistenceService.deleteNote(noteId)
     */
    @DeleteMapping("/note/{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable(value = "noteId") int noteId) {
        return noteService.deleteNote(noteId);
    }


}