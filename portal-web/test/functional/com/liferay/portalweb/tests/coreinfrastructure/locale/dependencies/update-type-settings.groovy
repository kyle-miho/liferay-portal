import com.liferay.portal.kernel.dao.jdbc.DataAccess
import com.liferay.portal.kernel.service.GroupLocalServiceUtil

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

long groupId = 37672

Connection connection = null
PreparedStatement ps = null
ResultSet rs = null

try {
	connection = DataAccess.getConnection()
	ps = connection.prepareStatement(
			"select typeSettings from Group_ where groupid="+groupId)

	rs = ps.executeQuery()
	while (rs.next()) {
		String typeSettings = rs.getString("typeSettings")
		GroupLocalServiceUtil.updateGroup(groupId, typeSettings)
	}
}
finally {
	DataAccess.cleanUp(connection, ps, rs)
	connection = null
}