package PersistenceService.Invitation;

import Model.Invitation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInvitationService {
    ResponseEntity<Void> addInvitation(String json);

    ResponseEntity<List<Invitation>> getInvitationList(int id);

    ResponseEntity<Void> deleteInvitation(int id);
}
