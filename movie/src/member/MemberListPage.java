package member;

import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MemberListPage extends JFrame{
	List list;
	
	public MemberListPage() {
		JPanel jpm = new JPanel();
		
		Label l1 = new Label("회원 관리 페이지", SwingConstants.CENTER);
		Label l2 = new Label("회원 목록");
		Label l3 = new Label("아이디 : ");
		Label l4 = new Label("이름 : ");
		Label l5 = new Label("비번 : ");
		Label l6 = new Label("전화번 : ");
		Label l7 = new Label("아이디 : ");
		Label l8 = new Label("이름 : ");
		Label l9 = new Label("비밀번호 : ");
		Label l10 = new Label("전화번호");
		
		add(l1);add(l2);add(l3);add(l4);add(l5);add(l6);add(l7);add(l8);add(l9);add(l10);
		
		TextField t1 = new TextField();
		TextField t2 = new TextField();
		TextField t3 = new TextField();
		TextField t4 = new TextField();
		
		add(t1);add(t2);add(t3);add(t4);
		
		Font font1 = new Font("맑은 고딕", Font.BOLD, 25);
		Font font2 = new Font("한컴 고딕", Font.BOLD, 16);
		
		l1.setFont(font1);
		l2.setFont(font2);
		
		JButton save = new JButton("저장");
		JButton delete = new JButton("삭제");
		JButton update = new JButton("수정");
		JButton search = new JButton("검색");
		JButton reset = new JButton("리셋");
		JButton back = new JButton("뒤로");
		
		add(save); add(delete); add(update); add(search); add(reset); add(back);
		
		list = new List();
		add(list);
		Font font3 = new Font("맑은 고딕", 0, 15);
		
		list.setFont(font3);
		
		l1.setBounds(300,30,200,40);
		l2.setBounds(50,200,80,30);
		l3.setBounds(350,100,50,30);
		l4.setBounds(350,130,50,30);
		l5.setBounds(550,100,60,30);
		l6.setBounds(550,130,60,30);
		l7.setBounds(70,230,60,25);
		l8.setBounds(250,230,60,25);
		l9.setBounds(450,230,60,25);
		l10.setBounds(650,230,60,25);
		
		
		t1.setBounds(410,100,100,30);
		t2.setBounds(410,130,100,30);
		t3.setBounds(610,100,100,30);
		t4.setBounds(610,130,100,30);
		
		save.setBounds(240,180,100,30);
		delete.setBounds(340,180,100,30);
		update.setBounds(440,180,100,30);
		search.setBounds(540,180,100,30);
		reset.setBounds(640,180,100,30);
		back.setBounds(650,670,100,30);

		list.setBounds(50,260,700,400);
		add(jpm);
		setSize(800,700);
		setTitle("회원관리");
		setResizable(false);
		setLocationRelativeTo(jpm);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//DB안에 있는 모든 자료를 list에 보이게 하기
		displayAll();

		list.addItemListener( new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String str = list.getSelectedItem();
				StringTokenizer st = new StringTokenizer(str);
				t1.setText(st.nextToken());
				t2.setText(st.nextToken());
				t3.setText(st.nextToken());
				t4.setText(st.nextToken());
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();
				String name = t2.getText();
				String pwd = t3.getText();
				String phoneNumber = t4.getText();
				
				MemberDAO dao = new MemberDAO();
				
				dao.insertMember(id, name, pwd, phoneNumber);
				JOptionPane.showMessageDialog(null, "저장완료");
				displayAll();
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();
				MemberDAO dao = new MemberDAO();
				dao.deleteMember(id);
				displayAll();
			}
		});
		
		update.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();
				String name = t2.getText();
				String pwd = t3.getText();
				String phoneNumber = t4.getText();
				
				MemberDAO dao = new MemberDAO();
				dao.update(id,name,pwd,phoneNumber);
				displayAll();
			}
		});
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
			}
		});
		
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//조회
				String id = t1.getText();
				MemberDAO dao = new MemberDAO();
				MemberDTO dto = dao.search(id);
								
				t1.setText(dto.getId());
				t2.setText(dto.getName());
				t3.setText(dto.getPwd());
				t4.setText(dto.getPhoneNumber());
			}
		});
	}

	private void displayAll() {
		list.removeAll();
		
		MemberDAO dao = new MemberDAO();
		dao.select(); //DB의 모든 자료를 리턴해줌
		ArrayList<MemberDTO> allData = dao.select();
		
		for(MemberDTO dto : allData) {
			String id = dto.getId();
			String name = dto.getName();
			String pwd = dto.getPwd();
			String phoneNumber = dto.getPhoneNumber();
			list.add(id+"                              "+name+"                              "+ pwd+ "                              "+phoneNumber);
		}
	}

}
