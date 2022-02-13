package PersistenceServer;

import Model.Invitation;
import PersistenceService.Invitation.IInvitationService;
import PersistenceService.Invitation.InvitationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvitationController {

    /**
     * Instance variable
     */
    private final IInvitationService invitationService;

    /**
     * Constructor for PersistenceServerController
     */
    public InvitationController() {
        this.invitationService = new InvitationService();
    }

    /**
     * Add invitation
     * @param requestBody
     * @return persistenceService.addInvitation(requestBody)
     */

    @PostMapping("/invitation")
    public ResponseEntity<Void> addInvitation(@RequestBody String requestBody) {
        return invitationService.addInvitation(requestBody);
    }

    /**
     * Get invitationlist
     * @param id
     * @return persistenceService.getInvitationList(id)
     */
    @GetMapping("/invitations/{id}")
    public ResponseEntity<List<Invitation>> getInvitationList(@PathVariable(value = "id") int id) {
        return invitationService.getInvitationList(id);
    }

    /**
     * Delete invitation
     * @param id
     * @return persistenceService.deleteInvitation(id)
     */
    @DeleteMapping("/invitation/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable(value = "id") int id) {
        return invitationService.deleteInvitation(id);
    }
}
