package PersistenceService.GroupMember;

import Model.GroupMembers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGroupMemberService {

    ResponseEntity<List<GroupMembers>> getGroupMemberList(int id);


    ResponseEntity<Void> addGroupMember(String json);

    ResponseEntity<Void> leaveGroup(int user_id, String json);

    ResponseEntity<Void> deleteGroupMember(int id);
}
