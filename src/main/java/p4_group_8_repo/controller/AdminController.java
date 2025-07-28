package p4_group_8_repo.controller;

import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static p4_group_8_repo.model.user.UserRole.MENTEE;
import static p4_group_8_repo.model.user.UserRole.MENTOR;

/**
 * The {@code AdminController} provides the list of mentees and mentors to be
 * displayed by the {@code AdminView} for the app's user interface .
 * It also contains the function to modify the password of a verified user
 */
public class AdminController {
    private final Repository<User, String> repo;

    /** constructor for the AdminController class*/
    public AdminController(Repository<User, String> model) {
        this.repo = model;
    }

    /** create the list of only mentees
     * @return the list of mentees */
    public ArrayList<String> menteeList(){
        List<User> userMenteeList = repo.selectAll();
        ArrayList<String> menteeList = new ArrayList<>();
        for(User user : userMenteeList){
            if(user.getRole() == MENTEE){
                menteeList.add(user.getUsername());
            }
        }
        return menteeList;
    }

    /** create the list of only available mentors
     *@return the list of only available mentors */
    public ArrayList<Mentor> mentorList() {
        List<User> userMentorList = repo.selectAll();
        ArrayList<Mentor> mentorList = new ArrayList<>();
        for (User user : userMentorList) {
            if (user.getRole() == MENTOR) {
                if (user instanceof Mentor mentor) {
                    String username = mentor.getUsername();
                    LocalDateTime startAvailability = mentor.getStartAvailability();
                    LocalDateTime endAvailability = mentor.getEndAvailability();
                    if(mentor.isAvailable()) {
                        mentorList.add(new Mentor(username, startAvailability, endAvailability));
                    }
                }
            }
        }
        return mentorList;
    }

    /** modifies password of the verified user
     *  @return true when password is changed*/
   public boolean onModifyClick(String currentUsername, String currentPassword,
                                String newPassword, String confirmPassword) {
        Optional<User> user = repo.selectById(currentUsername);
        if (user.isEmpty()) {
            return false;
        }
        User u = user.get();
        if(u.getUsername().equals(currentUsername) && u.getPassword().equals(currentPassword)){
            if(!Objects.equals(newPassword, "") && Objects.equals(newPassword, confirmPassword)){
                repo.update(new Administrator(currentUsername, newPassword));
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

}