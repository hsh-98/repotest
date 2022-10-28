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

	// java ���� ȭ���� awt, �������� ���� �� ����.

	public Login() {// ������ �������
		JPanel p = new JPanel();
		setLocationRelativeTo(p);

		Label l1 = new Label("��ȭ ���� �α���", SwingConstants.CENTER);

		Label l2 = new Label("���̵�");
		Label l3 = new Label("���");
		add(l1);
		add(l2);
		add(l3);

		TextField t1 = new TextField(); // input ���
		TextField t2 = new TextField(); // input ���

		add(t1);
		add(t2);

		t2.setEchoChar('*'); // ��й�ȣ�� *�� ������ �ϴ� �ڵ�

		JButton j1 = new JButton("�α���");
		JButton j2 = new JButton("ȸ������");

		add(j1);
		add(j2);

		Font font1 = new Font("���� ���", Font.BOLD, 30);

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
		setTitle("�α���");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		// ��ư �̺�Ʈ ó���ϱ�.. �α��� ��ư -> �̺�Ʈ ó��
		j1.addActionListener(new ActionListener() {
			@Override // �������̽��� �ٷ� �����ؼ� ����ϴ� �����!. ������ new�� ����/ �͸��Լ��� ����Ѱ�
			public void actionPerformed(ActionEvent e) {
				// �α��� ó��
				String id = t1.getText();
				String pwd = t2.getText();
				
				MemberDAO dao = new MemberDAO();
				int result = dao.login(id, pwd);
				
				if(result == 1) {
					if(id.equals("manager")){  //�����ڷ� �α���
						JOptionPane.showMessageDialog(null,"�����ڷ� �α����߽��ϴ�.");
						ManagerPage mangerPage = new ManagerPage(); //������ ȭ��
						dispose();
					}else { //�Ϲ� �������� �α��� ���� ���..
						JOptionPane.showMessageDialog(null, "�α��� �߽��ϴ�");
						SelectMenu menu = new SelectMenu(); //���� �������� �̵�
						dispose();
					}
				}else if(result == 0){ //�α��� ����
					JOptionPane.showMessageDialog(null, "�α��� ����");
					t1.setText("");
					t2.setText("");
				}
			}
		});

		// ȸ������ ��ư�� ������, ȸ������ �ϴ� ȭ������ �Ѿ.
		j2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȸ������ ȭ���� �߸� ��
				JoinMember joinMember = new JoinMember();
				dispose();
			}
		});
	}

}
