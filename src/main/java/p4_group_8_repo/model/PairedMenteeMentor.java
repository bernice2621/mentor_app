package p4_group_8_repo.model;

import p4_group_8_repo.model.user.Mentor;

public record PairedMenteeMentor(String mentee, Mentor mentor) {

    public String toString() {
        return String.format("Mentee: %s, Mentor: %s", mentee, mentor);
    }
}
