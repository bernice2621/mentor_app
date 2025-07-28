package p4_group_8_repo.model;

/**
 * Interface used to specify an entity's id type, used to make {@link HashMapRepository} work
 * generically.
 *
 * @param <Id> type used for id
 */
public interface HasId<Id> {

  Id getId();

  void setId(Id id);

}
