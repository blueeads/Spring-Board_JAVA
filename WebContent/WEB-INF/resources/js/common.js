/**
 * 
 */
	function getFormatData(date) {
		var year = date.getFullYear();
		var mon = date.getMonth()+1;
		var day = date.getDate();
		return year + "-" + mon + "-" + day;
	}