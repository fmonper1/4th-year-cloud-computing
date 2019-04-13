package ac.uk.shef.cc19grp10.auth.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.PreparedStatement;

/**
 * <Doc here>
 * <p>
 * Created on 13/04/2019.
 */
@Component
public class DbManagementRepositoryImpl implements DbManagementRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void createDbUser(String username, String password) {
		jdbcTemplate.update(conn -> {
			PreparedStatement stmt = conn.prepareStatement("CREATE USER IF NOT EXISTS ? IDENTIFIED BY ?");
			stmt.setString(1,username);
			stmt.setString(2,password);
			return stmt;
		});
	}


	/**
	 * DANGER: You must be really sure that the schema name you pass is safe.
	 */
	@Override
	public void createDbSchema(String schemaName) {
		jdbcTemplate.update(conn -> {
			PreparedStatement stmt = conn.prepareStatement("CREATE SCHEMA IF NOT EXISTS "+schemaName);
			return stmt;
		});
	}

	/**
	 * DANGER: You must be really sure that the schema name you pass is safe.
	 */
	@Override
	public void grantPrivileges(String schemaName, String userName) {
		jdbcTemplate.update(conn -> {
			PreparedStatement stmt = conn.prepareStatement("GRANT ALL PRIVILEGES ON "+schemaName+" TO ?");
			stmt.setString(1,userName);
			return stmt;
		});
	}
}
