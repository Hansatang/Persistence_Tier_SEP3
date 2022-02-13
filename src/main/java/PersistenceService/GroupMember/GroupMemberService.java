package PersistenceService.GroupMember;

import Model.GroupMembers;
import PersistenceService.PersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GroupMemberService extends PersistenceService implements IGroupMemberService{
    /**
     * Method leaveGroup removes group_id and user_id from groupmembers table
     * in the database.
     * @param id
     * @param json
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> leaveGroup(int id, String json) {
        String sqlStatement = "DELETE FROM notelender.groupmembers WHERE group_id = ? AND user_id = ?";
        try {
            PreparedStatement deleteGroupMember = connection.prepareStatement(sqlStatement);
            deleteGroupMember.setInt(1, id);
            deleteGroupMember.setInt(2, Integer.parseInt(json));
            deleteGroupMember.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    /**
     * Method which gets from the table groupmembers
     * and stores them in GroupMembersList to retrieve them
     * @param id
     * @return GroupMembersList, HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<List<GroupMembers>> getGroupMemberList(int id) {
        List<GroupMembers> GroupMembersList = new ArrayList<>();
        try {
            String sqlStatement = "SELECT notelender.groupmembers.id,user_id,u.username,group_id " +
                    "FROM notelender.groupmembers INNER JOIN notelender.users u ON u.id = groupmembers.user_id " +
                    "WHERE notelender.groupmembers.group_id = ?";
            PreparedStatement getUserList = connection.prepareStatement(sqlStatement);
            getUserList.setInt(1, id);
            ResultSet rs = getUserList.executeQuery();
            while (rs.next()) {
                GroupMembers groupMembers = new GroupMembers(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4));
                GroupMembersList.add(groupMembers);
            }
            return new ResponseEntity<>(GroupMembersList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Method adds new groupmember to groupmembers table in database
     * @param groupMember
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> addGroupMember(String groupMember) {
        String sqlStatement = "INSERT INTO notelender.groupmembers (user_id,group_id) VALUES (?,?)";
        GroupMembers groupMembers = gson.fromJson(groupMember, GroupMembers.class);
        try {
            PreparedStatement addGroupMember = connection.prepareStatement(sqlStatement);
            addGroupMember.setInt(1, groupMembers.getUserId());
            addGroupMember.setInt(2, groupMembers.getGroupId());
            addGroupMember.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Removes group members from groupmembers table in database using id
     * @param id
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> deleteGroupMember(int id) {
        String sqlStatement = "DELETE FROM notelender.groupmembers WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
