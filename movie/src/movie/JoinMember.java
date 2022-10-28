package movie;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import member.MemberDAO;

public class JoinMember extends JFrame {

	public JoinMember() {
		// 생성자에 짜주면.. 생성되자마자 화면을 뜨게 해줄 수 있음.

		JPanel p = new JPanel();

		setLocationRelativeTo(p);
		Label l1 = new Label("아이디");
		Label l2 = new Label("이름");
		Label l3 = new Label("비번");
		Label l4 = new Label("핸드폰");

		add(l1);
		add(l2);
		add(l3);
		add(l4);

		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();

		add(t1);
		add(t2);
		add(t3);
		add(t4);

		t3.setEchoChar('*');

		JButton b1 = new JButton("회원가입");
		JButton b2 = new JButton("취소");
		JButton b3 = new JButton("ID중복체크");

		add(b1);
		add(b2);
		add(b3);

		l1.setBounds(40, 10, 40, 30);
		l2.setBounds(40, 50, 40, 30);
		l3.setBounds(40, 90, 40, 30);
		l4.setBounds(40, 130, 40, 30);

		t1.setBounds(120, 10, 200, 30);
		t2.setBounds(120, 50, 200, 30);
		t3.setBounds(120, 90, 200, 30);
		t4.setBounds(120, 130, 200, 30);

		b1.setBounds(125, 330, 90, 30);
		b2.setBounds(240, 330, 80, 30);
		b3.setBounds(350, 10, 100, 30);

		add(p);

		setSize(500, 500);
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// 회원가입
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// text에서 값을 받아와야함
				String id = t1.getText();
				String name = t2.getText();
				String pwd = t3.getText();
				String phoneNumber = t4.getText();

				// DB연결 담당 클래스.. MemberDAO()
				MemberDAO dao = new MemberDAO();
				// dao 안에 삽입을 담당할 메서드를 구현하고 그걸 사용할 예정.

				dao.insertMember(id, name, pwd, phoneNumber);

				JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.");

				// 로그인 화면으로 돌아감
				Login login = new Login();
				dispose();

			}
		});

		// 취소
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원가입 창을 닫고, 다시 로그인 창으로
				Login login = new Login();
				dispose();
			}
		});

		// 중복체크
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();

				MemberDAO dao = new MemberDAO();
				if (dao.idCheck(id) == true) { // 사용중인 아이디는 true
					JOptionPane.showMessageDialog(null, "사용중인 아이디입니다");
					t1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다");
				}
			}
		});
	}
}
