package com.brainmentors.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brainmentors.chatapp.dto.UserDto;
import com.brainmentors.chatapp.utils.Encryption;
import com.mysql.cj.x.protobuf.MysqlxConnection.Close;
import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

// USER CRUD
public class UserDao {
   
	public boolean isLogin(UserDto userDto) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=CommonDao.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, userDto.getUserid());
			String encryptedPwd=Encryption.passwordEncrypt(new String(userDto.getPassword()));
			pstmt.setString(2, encryptedPwd);
			rs= pstmt.executeQuery();
			return rs.next();
		}
		finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
		}
		
	}
	
	public int add(UserDto userDto) throws ClassNotFoundException,SQLException, NoSuchAlgorithmException
	{
		System.out.println("Rec "+userDto.getUserid()+" "+ userDto.getPassword());
		Connection connection= null;
		Statement stmt=null;  //Query
		try {   //guarded region
		connection=CommonDao.createConnection();  //Step1- Connection create
		//Step-2
		stmt=connection.createStatement();
	
		int record=stmt.executeUpdate("insert into users (userid, password) values ('"+userDto.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDto.getPassword()))+"')");    //Insert, Delete, Update
		return record;
		}
		finally {    //always execute
			if(stmt!=null)
		stmt.close();
			if(connection!=null)
		connection.close();
		}
		
	}
   
   
}
