package member;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {

	// MySQL ����̹� ���� �����������
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movie"; // mysql ����
	// jdbc : java�� db(mysql)�� �������ִ� ����. �� �ڿ��� �¶��� �ּ���... �ּ� �������� aws������ ���ᵵ ������..
	// movie �� DB��
	String userid = "root";
	String password = "0000";

	// Ŀ�ؼ� �������̽�..
	Connection conn = null; // �������ִ� �������̽�

	Statement stmt = null; // Statement �������̽��� SQL�� ���� ����.. �ϰ�� SQL ���� �� �� �����
	PreparedStatement pstmt = null; // ������ ���µ�,, ??? �� ����ϸ� ������ ������ �� ����. ?�� ���߿� �ٸ� ������ ���� �� ����.

	ResultSet rs = null; // ���� ����� ���� ������.

	// �޼��� ����
	public void insertMember(String id, String name, String pwd, String phoneNumber) {

		// DB�� �����ϴ� �κ�.. ���� ������ �߻��ϴ� �κ����� ���� ó���� �ʿ���.
		try {
			conn = DriverManager.getConnection(url, userid, password);
			// ���� ������ conn�� ��´�.

			// ?�� ������ pstmt�� �������.
			String query = "insert into Member(id,name,pwd,phoneNumber) values (?,?,?,?)";
			pstmt = conn.prepareStatement(query); // �������� pstmt �� ���� �� �ִ� ���·� �������
			pstmt.setString(1, id); // 1�� ?�� ���� �ִ� �κ�
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, phoneNumber);
			// ������ �غ� ������

			// conn = DriverManager.getConnection(url, userid, password);
			// ���⿡�� getConnection�� �ϴϱ�
			// java.lang.NullPointerException: Cannot invoke
			// "java.sql.PreparedStatement.close()" because "this.pstmt" is null
			// ������ ����..

			// ���� ���.. ������ ����
			pstmt.executeUpdate(); // -> DB�� ������Ʈ�� ��

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // ���� ��� ���� ������ ����Ǵ� �κ�
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// mysql ����̹��� ������ �ȵǾ��ִ� ������.. JRE library�� mysql ����̹��� ����.. no suitable driver
		// ������ ��
		// mysql ����̹��� ��� �ִ��� build path�� ������ �������. external jars Ŭ���ؼ� C:\Program
		// Files(x86)\MySQL\Connector J 8.0 ���� jar�� �־���
		// �ڵ����� �������� �߰����ִ� ���̺귯���� ����. DI(������ ����)

	public boolean idCheck(String id) { // ������̶�� true�� �ѱ�
		boolean result = false;

		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "select id from member where id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery(); // return Ÿ���� result set��
			// executeQuery : �˻�
			// executeUpdate : ����, ����, ����

			if (rs.next()) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
	}

	public int login(String id, String pwd) {
		// �α��� üũ.. DB�� ���̵�� ��� ������ Ȯ��.

		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "select pwd from member where id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// ù ��° �÷��� ������ ����� ����.. �÷� ������ ������ �� ���� ����.
				// rs.getString('pwd')
				if (rs.getString(1).contentEquals(pwd))
					return 1; // �α��� ����
				else
					return 0; // ��й�ȣ�� �ٸ� ���..
			} else {
				return 0; // ���̵� ���� ���
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}

	// MemberDTO ��ü Ÿ������ ��Ƽ� ���
	// DB�� �ִ� ��� �״�� ������ ���ų� ���� �� ����ϴ� Ŭ����
	// DAO�� DB ���ῡ ����. ���� �� ��...
	public ArrayList<MemberDTO> select() {
		//DB���� ���� �˻���
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		//db���� �غ�
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "select * from member";
			pstmt = conn.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				dto.setId(rs.getString("id")); //db���ִ� id �÷��� ���� ������ �´�.
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setPhoneNumber(rs.getString("phoneNumber"));
				list.add(dto);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public void deleteMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "delete from member where id = ?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			int n = pstmt.executeUpdate(); //���� row�� ���� return��
			if(n >= 1) {
				System.out.println("���� ����");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(String id, String name, String pwd, String phoneNumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "update member set name=?, pwd=?, phoneNumber=? where id=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, id);
			
			int n = pstmt.executeUpdate();
			
			if(n >= 1) {
				System.out.println("���� ����");
			}else {
				System.out.println("���� ����");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public MemberDTO search(String id) {

				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				MemberDTO dto = new MemberDTO();
				
				try {
					conn = DriverManager.getConnection(url, userid, password);
					String query = "select * from member where id=?";
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, id);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						dto.setId(rs.getString("id")); //db���ִ� id �÷��� ���� ������ �´�.
						dto.setName(rs.getString("name"));
						dto.setPwd(rs.getString("pwd"));
						dto.setPhoneNumber(rs.getString("phoneNumber"));
					}
					
				}catch (Exception e) {
					e.printStackTrace();
				}finally {
					try {
						rs.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return dto;
	}
	

}
