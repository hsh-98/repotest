package movie;

import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import member.MemberDAO;

public class Login extends JFrame {

	// java 에서 화면은 awt, 스윙으로 만들 수 있음.

	public Login() {// 생성자 만들어줌
		JPanel p = new JPanel();
		setLocationRelativeTo(p);

		Label l1 = new Label("영화 예약 로그인", SwingConstants.CENTER);

		Label l2 = new Label("아이디");
		Label l3 = new Label("비번");
		add(l1);
		add(l2);
		add(l3);

		TextField t1 = new TextField(); // input 요소
		TextField t2 = new TextField(); // input 요소

		add(t1);
		add(t2);

		t2.setEchoChar('*'); // 비밀번호는 *로 나오게 하는 코드

		JButton j1 = new JButton("로그인");
		JButton j2 = new JButton("회원가입");

		add(j1);
		add(j2);

		Font font1 = new Font("맑은 고딕", Font.BOLD, 30);

		l1.setFont(font1);
		l1.setBounds(120, 10, 10000, 100); // x, y, weidth, height

		l2.setBounds(40, 200, 40, 40);
		l3.setBounds(40, 250, 40, 40);

		t1.setBounds(120, 200, 200, 30);
		t2.setBounds(120, 250, 200, 30);

		j1.setBounds(125, 330, 80, 30);
		j2.setBounds(240, 330, 90, 30);

		add(p);
		setSize(500, 500);
		setTitle("로그인");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 버튼 이벤트 처리하기.. 로그인 버튼 -> 이벤트 처리
		j1.addActionListener(new ActionListener() {
			@Override // 인터페이스를 바로 구현해서 사용하는 방법임!. 원래는 new를 못함/ 익명함수를 사용한것
			public void actionPerformed(ActionEvent e) {
				// 로그인 처리
				String id = t1.getText();
				String pwd = t2.getText();
				
				MemberDAO dao = new MemberDAO();
				int result = dao.login(id, pwd);
				
				if(result == 1) {
					if(id.equals("manager")){  //관리자로 로그인
						JOptionPane.showMessageDialog(null,"관리자로 로그인했습니다.");
						ManagerPage mangerPage = new ManagerPage(); //관리자 화면
						dispose();
					}else { //일반 계정으로 로그인 했을 경우..
						JOptionPane.showMessageDialog(null, "로그인 했습니다");
						SelectMenu menu = new SelectMenu(); //메인 페이지로 이동
						dispose();
					}
				}else if(result == 0){ //로그인 실패
					JOptionPane.showMessageDialog(null, "로그인 실패");
					t1.setText("");
					t2.setText("");
				}
			}
		});

		// 회원가입 버튼을 누르면, 회원가입 하는 화면으로 넘어감.
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 화면이 뜨면 됨
				JoinMember joinMember = new JoinMember();
				dispose();
			}
		});
	}

}
