package movie;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import member.MemberListPage;

public class ManagerPage extends JFrame{

	public ManagerPage() {
		JPanel jpmp = new JPanel();
		
		Label title = new Label("관리자 페이지", SwingConstants.CENTER);
		add(title);
		
		Font font1 = new Font("고딕체", Font.BOLD, 15);
		Font font2 = new Font("고딕체", Font.BOLD, 30);
		title.setFont(font2);
		
		title.setBounds(150,10,1000,65);
		
		JButton b1 = new JButton("회원 관리");
		JButton b2 = new JButton("영화정보 관리");
		JButton b3 = new JButton("예약 관리");
		JButton b4 = new JButton("로그아웃");
		
		add(b1);add(b2);add(b3);add(b4);
		
		b1.setBounds(165,100,150,40);
		b2.setBounds(165,150,150,40);
		b3.setBounds(165,200,150,40);
		b4.setBounds(165,370,150,30);
		
		add(jpmp);
		setSize(500,450);
		setLocationRelativeTo(jpmp);
		setResizable(false);
		setTitle("관리자페이지");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//회원관리
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MemberListPage memberListPage = new MemberListPage();
			}
		});
		
		//영화정보 관리
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//예약관리
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//로그아웃
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
