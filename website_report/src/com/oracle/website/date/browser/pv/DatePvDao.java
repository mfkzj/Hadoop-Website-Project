package com.oracle.website.date.browser.pv;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.blog.connection.JDBCMannger;


public class DatePvDao {
	private String type;
	
	public DatePvDao (String type) {
		this.type = type;
	}
	
	public DatePvEntity getDateUserList() {
		Connection con = JDBCMannger.getConnection();
		List<String> xdate = new ArrayList<>();
		List<String> newVals = new ArrayList<>();
		List<String> todayVals = new ArrayList<>();
//		List<String> totalVals = new ArrayList<>();
		DatePvEntity dateUserEntity = new DatePvEntity();
		try {
			PreparedStatement ps = con.prepareStatement("select date,browserinfo,pv,avgpv "
					+ "from website_date_browser_pv_count");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String date = rs.getString(1);
				String browserType = rs.getString(2);
				String newVal = rs.getString(3);
				String todayVal = rs.getString(4);
//				String totalVal = rs.getString(5);
//				System.out.println(date);
				if (type.equals(browserType)) {
					xdate.add(date);
					newVals.add(newVal);
					todayVals.add(todayVal);
//					totalVals.add(totalVal);
				}
				if (type.equals("Null")) {
					xdate.add("");
					newVals.add("");
					todayVals.add("");
//					totalVals.add("");
				}
				
			}
			dateUserEntity.setBrand(xdate);
			dateUserEntity.setListData(newVals);
			dateUserEntity.setListData1(todayVals);
//			dateUserEntity.setListData2(totalVals);
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dateUserEntity;
	}

}