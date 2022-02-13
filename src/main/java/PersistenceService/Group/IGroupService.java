package PersistenceService.Group;

import Model.Group;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGroupService {

    ResponseEntity<Void> postGroup(String json, int memberId);

    ResponseEntity<Void> deleteGroup(int id);

    ResponseEntity<List<Group>> getGroupList(int id);
}
