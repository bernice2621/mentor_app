package p4_group_8_repo.model.user;

/**
 * Interface for any entity that can have a CV.
 */
public interface HasCv {

  String getCvText();

  void setCvText(String text);
}
