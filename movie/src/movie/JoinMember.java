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
		// �����ڿ� ¥�ָ�.. �������ڸ��� ȭ���� �߰� ���� �� ����.

		JPanel p = new JPanel();

		setLocationRelativeTo(p);
		Label l1 = new Label("���̵�");
		Label l2 = new Label("�̸�");
		Label l3 = new Label("���");
		Label l4 = new Label("�ڵ���");

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

		JButton b1 = new JButton("ȸ������");
		JButton b2 = new JButton("���");
		JButton b3 = new JButton("ID�ߺ�üũ");

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
		setTitle("ȸ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ȸ������
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// text���� ���� �޾ƿ;���
				String id = t1.getText();
				String name = t2.getText();
				String pwd = t3.getText();
				String phoneNumber = t4.getText();

				// DB���� ��� Ŭ����.. MemberDAO()
				MemberDAO dao = new MemberDAO();
				// dao �ȿ� ������ ����� �޼��带 �����ϰ� �װ� ����� ����.

				dao.insertMember(id, name, pwd, phoneNumber);

				JOptionPane.showMessageDialog(null, "ȸ�� ������ �Ϸ�Ǿ����ϴ�.");

				// �α��� ȭ������ ���ư�
				Login login = new Login();
				dispose();

			}
		});

		// ���
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȸ������ â�� �ݰ�, �ٽ� �α��� â����
				Login login = new Login();
				dispose();
			}
		});

		// �ߺ�üũ
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = t1.getText();

				MemberDAO dao = new MemberDAO();
				if (dao.idCheck(id) == true) { // ������� ���̵�� true
					JOptionPane.showMessageDialog(null, "������� ���̵��Դϴ�");
					t1.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "��� ������ ���̵��Դϴ�");
				}
			}
		});
	}
}
