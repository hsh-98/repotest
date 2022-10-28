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
		
		Label title = new Label("������ ������", SwingConstants.CENTER);
		add(title);
		
		Font font1 = new Font("���ü", Font.BOLD, 15);
		Font font2 = new Font("���ü", Font.BOLD, 30);
		title.setFont(font2);
		
		title.setBounds(150,10,1000,65);
		
		JButton b1 = new JButton("ȸ�� ����");
		JButton b2 = new JButton("��ȭ���� ����");
		JButton b3 = new JButton("���� ����");
		JButton b4 = new JButton("�α׾ƿ�");
		
		add(b1);add(b2);add(b3);add(b4);
		
		b1.setBounds(165,100,150,40);
		b2.setBounds(165,150,150,40);
		b3.setBounds(165,200,150,40);
		b4.setBounds(165,370,150,30);
		
		add(jpmp);
		setSize(500,450);
		setLocationRelativeTo(jpmp);
		setResizable(false);
		setTitle("������������");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//ȸ������
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MemberListPage memberListPage = new MemberListPage();
			}
		});
		
		//��ȭ���� ����
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//�������
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		//�α׾ƿ�
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}
}
