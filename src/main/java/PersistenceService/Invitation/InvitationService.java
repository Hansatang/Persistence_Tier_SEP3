package PersistenceService.Invitation;

import Model.Invitation;
import PersistenceService.PersistenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InvitationService extends PersistenceService implements IInvitationService{

    /**
     * Inserts new invitation in invitations table in database
     * @param invitation
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> addInvitation(String invitation) {
        {

            Invitation temp = gson.fromJson(invitation, Invitation.class);
            String sqlStatement = "INSERT INTO notelender.invitations (invitor_id,invitee_id,group_id) VALUES (?,?,?)";
            try {
                PreparedStatement addInvitation = connection.prepareStatement(sqlStatement);
                addInvitation.setInt(1, temp.getInvitorId());
                addInvitation.setInt(2, temp.getInviteeId());
                addInvitation.setInt(3, temp.getGroupId());
                addInvitation.executeUpdate();
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
    }
    /**
     * Removes invitation from invitations table in database
     * @param id
     * @return HttpStatus.OK or HttpStatus.BAD_REQUEST
     */
    @Override
    public ResponseEntity<Void> deleteInvitation(int id) {
        String sqlStatement = "DELETE FROM notelender.invitations WHERE id= ?";
        try {
            PreparedStatement deleteInvitation = connection.prepareStatement(sqlStatement);
            deleteInvitation.setInt(1, id);
            deleteInvitation.executeUpdate();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * Method which gets from the table groupmembers
     * and stores them in InvitationList to retrieve the invitations
     * @param id
     * @return
     */
    @Override
    public ResponseEntity<List<Invitation>> getInvitationList(int id) {
        List<Invitation> InvitationList = new ArrayList<>();
        try {
            String sqlStatement = "SELECT invitations.id, g.id, g.groupname, invitations.invitee_id, u2.username, " +
                    "invitations.invitor_id, u.username FROM notelender.invitations\n" +
                    "         INNER JOIN notelender.groups g ON g.id = invitations.group_id\n" +
                    "         INNER JOIN notelender.users u ON u.id = invitations.invitor_id\n" +
                    "         INNER JOIN notelender.users u2 ON u2.id = invitations.invitee_id\n" +
                    "WHERE invitee_id = ?";
            PreparedStatement getInvitation = connection.prepareStatement(sqlStatement);
            getInvitation.setInt(1, id);
            ResultSet rs = getInvitation.executeQuery();
            while (rs.next()) {
                InvitationList.add(new Invitation(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5),
                        rs.getInt(6), rs.getString(7)));
            }
            return new ResponseEntity<>(InvitationList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
