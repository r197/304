package queries;

import java.sql.SQLException;

/**
 * Holds the semantics of a query and the ability to execute it on a database
 */
public interface IQuery<R> {

	public R execute() throws SQLException;
}
