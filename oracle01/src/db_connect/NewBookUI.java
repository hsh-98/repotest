package db_connect;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class NewBookUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewBookUI frame = new NewBookUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewBookUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		contentPane = new JPanel();
		Label title = new Label("신간도서",SwingConstants.CENTER);
		
		Label l1 = new Label("책 제목");
		Label l2 = new Label("저자");
		Label l3 = new Label("출판사");
		Label l4 = new Label("표지주소");
		
		TextField book_title = new TextField();
		TextField author = new TextField();
		TextField publisher = new TextField();
		TextField url = new TextField();
		
		Font font1 = new Font("궁서체", Font.BOLD, 15);
		
		title.setBounds(200, 30, 100, 50);
		
		l1.setBounds(60,100,70, 40);
		l2.setBounds(60,150,70, 40);
		l3.setBounds(60,200,70, 40);
		l4.setBounds(60,250,70, 40);

		book_title.setBounds(130, 100, 250, 40);
		author.setBounds(130, 150, 250, 40);
		publisher.setBounds(130, 200, 250, 40);
		url.setBounds(130, 250, 250, 40);
		
		title.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		
		add(title);
		
		l1.setFont(font1);
		l2.setFont(font1);
		l3.setFont(font1);
		l4.setFont(font1);
		
		book_title.setFont(font1);
		author.setFont(font1);		
		publisher.setFont(font1);		
		url.setFont(font1);		
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		
		add(book_title);
		add(author);
		add(publisher);
		add(url);
		
		JButton b1 = new JButton("도서추가");
		JButton b2 = new JButton("뒤로");
		
		b1.setBounds(130, 310, 100, 40);
		b2.setBounds(280, 310, 100, 40);
		
		//도서추가 버튼 클릭
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//book db로 책 추가 쿼리 날림
				dispose();
			}
		});

		//뒤로 버튼 클릭
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//관리자 메인화면으로
				dispose();
			}
		});
		
		add(b1);
		add(b2);
		
		add(contentPane);
		
		
		
		
	}

}
