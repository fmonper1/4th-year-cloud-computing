package ac.uk.shef.cc19grp10.dashboard.data;

/**
 * <Doc here>
 * <p>
 * Created on 13/04/2019.
 */
public interface DbManagementRepository {
	void createDbUser(String username, String password);
	/**
	 * DANGER: You must be really sure that the schema name you pass is safe.
	 */
	void createDbSchema(String schemaName);
	/**
	 * DANGER: You must be really sure that the schema name you pass is safe.
	 */
	void grantPrivileges(String schemaName, String userName);
}
