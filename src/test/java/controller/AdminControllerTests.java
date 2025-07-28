package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import p4_group_8_repo.controller.AdminController;
import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.Administrator;
import p4_group_8_repo.model.user.Mentee;
import p4_group_8_repo.model.user.Mentor;
import p4_group_8_repo.model.user.User;

import java.util.List;
import java.util.Optional;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static p4_group_8_repo.model.user.UserRole.*;


public class AdminControllerTests {
    private Repository<User, String> model;
    private AdminController controller;

    @BeforeEach
    public void setUp() {
        model = mock();
        controller = new AdminController(model);
        // Mocking the Mentee, Mentor and Administrator objects
        Mentee mentee1 = mock(Mentee.class);
        Mentee mentee2 = mock(Mentee.class);
        Mentor mentor1 = mock(Mentor.class);
        Mentor mentor2 = mock(Mentor.class);
        Administrator admin1 = mock(Administrator.class);

        when(mentee1.getRole()).thenReturn(MENTEE);
        when(mentee1.getUsername()).thenReturn("msalters2");
        when(mentee1.getPassword()).thenReturn("cO4<hv{&s5)");

        when(mentee2.getRole()).thenReturn(MENTEE);
        when(mentee2.getUsername()).thenReturn("rforrestill3");
        when(mentee2.getPassword()).thenReturn("wE2>2~nHhwNyc");


        when(mentor1.getRole()).thenReturn(MENTOR);
        when(mentor1.getUsername()).thenReturn("pmountforda");
        when(mentor1.getPassword()).thenReturn("uV3>nOUy=");
        when(mentor1.isAvailable()).thenReturn(false);


        when(mentor2.getRole()).thenReturn(MENTOR);
        when(mentor2.getUsername()).thenReturn("jbasindaleb");
        when(mentor2.getPassword()).thenReturn("lI5\\I}/'q_R/A");
        when(mentor2.isAvailable()).thenReturn(true);

        when(admin1.getRole()).thenReturn(ADMIN);
        when(admin1.getUsername()).thenReturn("sdenyakin1f");
        when(admin1.getPassword()).thenReturn("iW1*iQ)_q");
        when(model.selectAll()).thenReturn(List.of(mentee1, mentee2, mentor1, mentor2, admin1));
    }

    @Test
    public void testMenteeListReturnsOnlyMentees() {
        List<String> menteeList = controller.menteeList();
        //testing that it contains the two mentees
        assertTrue(menteeList.contains("msalters2"));
        assertTrue(menteeList.contains("rforrestill3"));
        assertFalse(menteeList.contains("pmountforda"));
        assertFalse(menteeList.contains("jbasindaleb"));
        assertFalse(menteeList.contains("jbasindaleb"));
    }

    @Test
    public void testMenteeListReturnsCorrectNumberOfMentees() {
        List<String> menteeList = controller.menteeList();

        assertNotEquals(0,menteeList.size());
        assertEquals(2, menteeList.size());
    }

    @Test
    public void testMentorListReturnsCorrectNumberOfMentors() {
        List<Mentor> mentorList = controller.mentorList();

        assertNotEquals(0,mentorList.size());
        assertEquals(1, mentorList.size());
    }

    @Test
    public void testMentorListReturnsOnlyAvailableMentor() {
        List<Mentor> mentorList = controller.mentorList();

        assertTrue(mentorList.stream().anyMatch(mentor -> "jbasindaleb".equals(mentor.getUsername())));
        assertFalse(mentorList.contains("msalters2"));
        assertFalse(mentorList.contains("rforrestill3"));
        assertFalse(mentorList.stream().anyMatch(mentor -> "pmountforda".equals(mentor.getUsername())));
        assertFalse(mentorList.contains("sdenyakin1f"));
    }

    @Test
    public void testMentorListDoesNotReturnUnavailableMentor() {
        List<Mentor> mentorList = controller.mentorList();
        assertFalse(mentorList.stream().anyMatch(mentor -> "pmountforda".equals(mentor.getUsername())));
    }

    @Test
    public void testModifyClickCorrectDetailsAndPasswords() {
        when(model.selectById("sdenyakin1f")).thenReturn(Optional.of(new Administrator("sdenyakin1f", "iW1*iQ)_q")));
        boolean m = controller.onModifyClick("sdenyakin1f","iW1*iQ)_q","bernice", "bernice" );
        assertTrue(m);
    }

    @Test
    public void testModifyClickMissingUser() {
        when(model.selectById("sdenyakin1f")).thenReturn(Optional.of(new Administrator("sdenyakin1f", "iW1*iQ)_q")));
        boolean m = controller.onModifyClick("wrongusername","iW1*iQ)_q","bernice", "bernice" );
        assertFalse(m);
    }

    @Test
    public void testModifyClickWrongCurrentPassword() {
        when(model.selectById("sdenyakin1f")).thenReturn(Optional.of(new Administrator("sdenyakin1f", "iW1*iQ)_q")));
        boolean m = controller.onModifyClick("sdenyakin1f","wrongpassword","bernice", "bernice" );
        assertFalse(m);
    }

    @Test
    public void testModifyClickNewPasswordConfirmationFailed() {
        when(model.selectById("sdenyakin1f")).thenReturn(Optional.of(new Administrator("sdenyakin1f", "iW1*iQ)_q")));
        boolean m = controller.onModifyClick("sdenyakin1f","wrongpassword","bernice", "kayla" );
        assertFalse(m);
    }

}
