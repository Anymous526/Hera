Ext.Ajax.on('requestcomplete', checkUserSessionStatus, this);
function checkUserSessionStatus(conn, response, options) {
	if (typeof response.getResponseHeader != 'undefined') {
		if (response.getResponseHeader('sessionstatus') == 'true') {
			topWin = window;
			while (topWin.parent != topWin.self) {
				topWin = topWin.parent;
			}
			topWin.location = contextPath + '/view/security/jsp/login.jsp?login_error=session_expired';
		}
	}
}
