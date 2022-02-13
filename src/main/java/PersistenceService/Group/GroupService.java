package PersistenceService.Group;

import Model.Group;
import PersistenceService.PersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupService extends PersistenceService implements IGroupService{

    /**
     * Add Group adds new group to both the table groups,
     * and groupmembers in database
     * @param name
     * @param memberId
     * @return HttpStatus.OK or  HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> postGroup(String name, int memberId) {
        String sqlStatement = "WITH groupcreation AS ( INSERT INTO notelender.groups (groupname) VALUES (?) " +
                "RETURNING id) INSERT INTO notelender.groupmembers (user_id, group_id)\n" +
                "VALUES ( ?, (SELECT id FROM groupcreation))";
        try {
            PreparedStatement insertGroup = connection.prepareStatement(sqlStatement);
            insertGroup.setString(1, name);
            insertGroup.setInt(2, memberId);
            insertGroup.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method which takes groups from the table groupmembers and groups
     * and stores them in GroupList to retrieve them
     * @param id
     * @return GroupList, HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<List<Group>> getGroupList(int id) {
        List<Group> GroupList = new ArrayList<>();
        try {
            String sqlStatement = "SELECT g.id, groupname FROM notelender.groupmembers\n" +
                    " INNER JOIN notelender.groups g ON g.id = groupmembers.group_id\n" +
                    "WHERE user_id = ?";
            PreparedStatement getGroupMembersList = connection.prepareStatement(sqlStatement);
            getGroupMembersList.setInt(1, id);
            ResultSet rs = getGroupMembersList.executeQuery();
            while (rs.next()) {
                GroupList.add(new Group(rs.getInt(1), rs.getString(2)));
            }
            return new ResponseEntity<>(GroupList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete group from table groups in database.
     * @param group_id
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> deleteGroup(int group_id) {
        String sqlStatement = "DELETE FROM notelender.groups WHERE id= ?";
        try {
            PreparedStatement deleteGroup = connection.prepareStatement(sqlStatement);
            deleteGroup.setInt(1, group_id);
            deleteGroup.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
