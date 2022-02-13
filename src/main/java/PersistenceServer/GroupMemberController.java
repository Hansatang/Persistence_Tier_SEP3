package PersistenceServer;

import Model.GroupMembers;
import PersistenceService.GroupMember.GroupMemberService;
import PersistenceService.GroupMember.IGroupMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupMemberController {

    /**
     * Instance variable
     */
    private final IGroupMemberService groupMemberService;

    /**
     * Constructor for PersistenceServerController
     */
    public GroupMemberController() {
        this.groupMemberService = new GroupMemberService();
    }

    /**
     * Add group member
     *
     * @param requestBody
     * @return persistenceService.addGroupMember(requestBody)
     */
    @PostMapping("/groupmembers")
    public ResponseEntity<Void> AddGroupMember(@RequestBody String requestBody) {
        return groupMemberService.addGroupMember(requestBody);
    }

    /**
     * Get GroupMemberList
     *
     * @param id
     * @return persistenceService.getGroupMemberList(id)
     */
    @GetMapping("/groupmemberslist/{id}")
    public ResponseEntity<List<GroupMembers>> getGroupMemberList(@PathVariable(value = "id") int id) {
        return groupMemberService.getGroupMemberList(id);
    }

    /**
     * Delete group member
     *
     * @param id
     * @return persistenceService.deleteGroupMember(id)
     */
    @DeleteMapping("/groupmembers/{id}")
    public ResponseEntity<Void> deleteGroupMember(@PathVariable(value = "id") int id) {
        return groupMemberService.deleteGroupMember(id);
    }

    /**
     * Leave group
     *
     * @param id
     * @param requestBody
     * @return
     */
    @PostMapping("/groupmembers/{id}")
    public ResponseEntity<Void> LeaveGroup(@PathVariable(value = "id") int id, @RequestBody String requestBody) {
        return groupMemberService.leaveGroup(id, requestBody);
    }
}
