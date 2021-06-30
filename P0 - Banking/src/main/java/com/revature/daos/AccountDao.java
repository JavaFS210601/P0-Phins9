package com.revature.daos;

import java.sql.*;
import java.util.Date;
import java.text.*;
import java.util.*;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

public class AccountDao implements AccountDaoInterface {

	@Override
	public List<Account> getAccounts() {

		try (Connection conn = ConnectionUtil.getConnection()) {
			ResultSet rs = null;
			
			String sql = "SELECT * FROM accounts;";
			
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Account> accountList = new ArrayList<>();
			
			while (rs.next()) {
				Account account = new Account (
						rs.getInt("account_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						rs.getString("join_date"),
						rs.getInt("balance"),
						rs.getInt("roles")
						);
				accountList.add(account);
			}
			
			return accountList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong when trying to access your DB");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public void addAccount(Account acc) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd");
			
			Date date = new Date();
			
			String currentDate = dateFormate.format(date);
			
			String sql = "INSERT INTO accounts (first_name, last_name, join_date, balance, roles) VALUES (?, ?, ?, ?, ?);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1,  acc.getF_name());
			ps.setString(2, acc.getL_name());
			ps.setDate(3,  java.sql.Date.valueOf(currentDate));
			ps.setInt(4, acc.getRole_id());
			ps.setInt(5, acc.getRoles_id());
			
			ps.executeUpdate();
			
			System.out.println("Account for " + acc.getF_name() + " created!");
			
			
		} catch (SQLException e) {
			System.out.println("Add account failed!");
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void changeBalance(int accId, int balance) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, balance);
			ps.setInt(2, accId);
			
			ps.executeUpdate();
			
			System.out.println("Account balance updated!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	@Override
	public void changeRole(int accId, int roles) {
		
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "UPDATE accounts SET roles = ? WHERE account_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, roles);
			ps.setInt(2, accId);
			
			ps.executeUpdate();
			
			System.out.println("Account role updated!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}

	@Override
	public void removeAccount(int accId) {

		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "DELETE FROM accounts WHERE account_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, accId);
			
			ps.executeUpdate();
			
			System.out.println("Account deleted!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
