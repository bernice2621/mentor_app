package p4_group_8_repo.controller;

import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.User;

public class MentorController {
    private final Repository<User, String> repo;

    public MentorController(Repository<User, String> model) {
        this.repo = model;
    }

}
