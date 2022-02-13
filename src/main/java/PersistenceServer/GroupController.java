package PersistenceServer;

import Model.Group;
import PersistenceService.Group.GroupService;
import PersistenceService.Group.IGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    /**
     * Instance variable
     */
    private final IGroupService groupService;

    /**
     * Constructor for PersistenceServerController
     */
    public GroupController() {
        this.groupService = new GroupService();
    }

    /**
     * Get group list
     * @param id
     * @return persistenceService.getGroupList(id)
     */
    @GetMapping("/groups/{id}")
    public ResponseEntity<List<Group>> getGroupList(@PathVariable(value = "id") int id) {
        return groupService.getGroupList(id);
    }

    /**
     * Create group
     * @param requestBody
     * @param memberId
     * @return persistenceService.postGroup(requestBody, memberId)
     */
    @PostMapping("/group/{memberId}")
    public ResponseEntity<Void> createGroup(@RequestBody String requestBody, @PathVariable(value = "memberId") int memberId) {
        return groupService.postGroup(requestBody, memberId);
    }

    /**
     * Delete group
     * @param id
     * @return persistenceService.deleteGroup(id)
     */
    @DeleteMapping("/group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable(value = "id") int id) {
        return groupService.deleteGroup(id);
    }


}