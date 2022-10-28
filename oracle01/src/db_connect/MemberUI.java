package db_connect;

import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class MemberUI{

	/**  
	 * Launch the application.
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		JPanel p = new JPanel();
		f.setLocationRelativeTo(p);
		f.setSize(500,500);
		
		Label title = new Label("회원 가입",SwingConstants.CENTER);
		
		Label l1 = new Label("ID");
		Label l2 = new Label("PW");
		Label l3 = new Label("NAME");
		Label l4 = new Label("TEL");
		
		TextField id = new TextField();
		TextField pwd = new TextField();
		TextField name = new TextField();
		TextField tel = new TextField();
		
		Font font1 = new Font("궁서체", Font.BOLD, 15);
		
		title.setBounds(200, 30, 100, 50);
		
		l1.setBounds(60,100,50, 40);
		l2.setBounds(60,150,50, 40);
		l3.setBounds(60,200,50, 40);
		l4.setBounds(60,250,50, 40);

		id.setBounds(130, 100, 250, 40);
		pwd.setBounds(130, 150, 250, 40);
		name.setBounds(130, 200, 250, 40);
		tel.setBounds(130, 250, 250, 40);
		
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		f.add(title);
		
		l1.setFont(font1);
		l2.setFont(font1);
		l3.setFont(font1);
		l4.setFont(font1);
		
		id.setFont(font1);
		pwd.setFont(font1);		
		name.setFont(font1);		
		tel.setFont(font1);		
		
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		
		f.add(id);
		f.add(pwd);
		f.add(name);
		f.add(tel);
		
		
		
		JButton b1 = new JButton("회원가입");
		JButton b2 = new JButton("취소");
		
		b1.setBounds(130, 310, 100, 40);
		b2.setBounds(280, 310, 100, 40);
		  
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				f.dispose();
			}
		});

		  
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		      
				f.dispose();
			}
		});
		
		f.add(b1);
		f.add(b2);
		
		f.add(p);
		
		f.setVisible(true);
	}

}
