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

	// MySQL 드라이버 명을 선언해줘야함
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/movie"; // mysql 연결
	// jdbc : java랑 db(mysql)를 연결해주는 것임. 그 뒤에는 온라인 주소임... 주소 설정으로 aws등으로 연결도 가능함..
	// movie 는 DB명
	String userid = "root";
	String password = "0000";

	// 커넥션 인터페이스..
	Connection conn = null; // 연결해주는 인터페이스

	Statement stmt = null; // Statement 인테페이스에 SQL을 쓰는 것임.. 완결된 SQL 문을 쓸 때 사용함
	PreparedStatement pstmt = null; // 문장을 쓰는데,, ??? 를 사용하며 문장을 구성할 수 있음. ?에 나중에 다른 값들을 넣을 수 있음.

	ResultSet rs = null; // 쿼리 결과를 담을 공간임.

	// 메서드 구현
	public void insertMember(String id, String name, String pwd, String phoneNumber) {

		// DB와 연결하는 부분.. 실제 연결이 발생하는 부분으로 예외 처리가 필요함.
		try {
			conn = DriverManager.getConnection(url, userid, password);
			// 연결 정보를 conn에 담는다.

			// ?가 있으니 pstmt를 써줘야함.
			String query = "insert into Member(id,name,pwd,phoneNumber) values (?,?,?,?)";
			pstmt = conn.prepareStatement(query); // 쿼리문을 pstmt 가 담을 수 있는 형태로 만들어줌
			pstmt.setString(1, id); // 1번 ?에 값을 넣는 부분
			pstmt.setString(2, name);
			pstmt.setString(3, pwd);
			pstmt.setString(4, phoneNumber);
			// 쿼리문 준비가 끝났음

			// conn = DriverManager.getConnection(url, userid, password);
			// 여기에서 getConnection을 하니까
			// java.lang.NullPointerException: Cannot invoke
			// "java.sql.PreparedStatement.close()" because "this.pstmt" is null
			// 에러가 났음..

			// 실행 명령.. 쿼리문 실행
			pstmt.executeUpdate(); // -> DB에 업데이트가 됨

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 예외 상관 없이 무조건 수행되는 부분
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}// mysql 드라이버가 연결이 안되어있는 상태임.. JRE library에 mysql 드라이버가 없음.. no suitable driver
		// 에러가 남
		// mysql 드라이버가 어디에 있는지 build path로 포인팅 해줘야함. external jars 클릭해서 C:\Program
		// Files(x86)\MySQL\Connector J 8.0 에서 jar를 넣어줌
		// 자동으로 의존성을 추가해주는 라이브러리도 있음. DI(의존성 주입)

	public boolean idCheck(String id) { // 사용중이라면 true를 넘김
		boolean result = false;

		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "select id from member where id = ?";

			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery(); // return 타입은 result set임
			// executeQuery : 검색
			// executeUpdate : 삽입, 삭제, 수정

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
		// 로그인 체크.. DB에 아이디와 비번 보내서 확인.

		try {
			conn = DriverManager.getConnection(url, userid, password);
			String query = "select pwd from member where id = ?";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// 첫 번째 컬럼을 가지고 오라는 것임.. 컬렴 명으로 가지고 올 수도 있음.
				// rs.getString('pwd')
				if (rs.getString(1).contentEquals(pwd))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호가 다른 경우..
			} else {
				return 0; // 아이디가 없는 경우
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

	// MemberDTO 객체 타입으로 담아서 줘용
	// DB에 있는 모습 그대로 가지고 오거나 넣을 때 사용하는 클래스
	// DAO는 DB 연결에 사용됨. 쿼리 문 등...
	public ArrayList<MemberDTO> select() {
		//DB에서 모든걸 검색함
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		//db연결 준비
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
				dto.setId(rs.getString("id")); //db에있는 id 컬럼의 값을 가지고 온다.
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
			int n = pstmt.executeUpdate(); //지운 row의 수를 return함
			if(n >= 1) {
				System.out.println("삭제 성공");
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
				System.out.println("수정 성공");
			}else {
				System.out.println("수정 실패");
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
						dto.setId(rs.getString("id")); //db에있는 id 컬럼의 값을 가지고 온다.
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
