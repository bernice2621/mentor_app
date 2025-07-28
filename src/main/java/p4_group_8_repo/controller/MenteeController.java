package p4_group_8_repo.controller;

import p4_group_8_repo.model.Repository;
import p4_group_8_repo.model.user.User;

public class MenteeController {
    private final Repository<User, String> repo;

    public MenteeController(Repository<User, String> model) {
        this.repo = model;
    }


}
