package �ڵ����;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("�迭 ������ �Է� : ");
		
		int n = sc.nextInt();
		
		List<Integer> arr = new ArrayList<Integer>();
		
		for(int i = 0; i < n; i++) {
			System.out.print((i+1) + "��° ���� �Է� : ");
			arr.add(sc.nextInt());
		}
		
		int find = 0;

		while(true) {
			System.out.print("ã�� �� �Է�(��� : -1) : ");
		    find = sc.nextInt();
		    if(find == -1) {
		    	break;
		    }else {
		    	if(arr.contains(find)) {
		    		System.out.println(find + "�� ����Ʈ�� �ֽ��ϴ�.");
		    	}else {
		    		System.out.println(find + "�� ����Ʈ�� �����ϴ�.");
		    	}
		    }
		}
	}
	/*
	 * ������ 5����  ����Ʈ�ȿ� �ְ�(����)
	    ������ ã�� �ڵ带 �����ϱ�
	    ��������Ʈ�� �����ص� �ǰ�
	    ���߿��Ḯ��Ʈ�� �����ص� �ǰ�
	    ���Ͽ��Ḯ��Ʈ�� �����ص� �ǰ�
	    java.util.List �� �̿��ؼ� �����ص� �ǰ�
	    ����Ʈ�� ũ���? 5
	    ������? 10
	    ������? 5
	    ������? 3
	    ������? 4
	    ������? 7
	    �˻��� ������? 5
	    5�� �ڷ�� ����Ʈ�� �ֽ��ϴ�
	    �Ǵ�
	    �˻��� ������? 9
	    9�� �ڷ�� ����Ʈ�� �����ϴ�
	 * */
}
