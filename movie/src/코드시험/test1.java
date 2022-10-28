package 코드시험;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class test1 {

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("배열 사이즈 입력 : ");
		
		int n = sc.nextInt();
		
		List<Integer> arr = new ArrayList<Integer>();
		
		for(int i = 0; i < n; i++) {
			System.out.print((i+1) + "번째 정수 입력 : ");
			arr.add(sc.nextInt());
		}
		
		int find = 0;

		while(true) {
			System.out.print("찾을 값 입력(취소 : -1) : ");
		    find = sc.nextInt();
		    if(find == -1) {
		    	break;
		    }else {
		    	if(arr.contains(find)) {
		    		System.out.println(find + "는 리스트에 있습니다.");
		    	}else {
		    		System.out.println(find + "는 리스트어 없습니다.");
		    	}
		    }
		}
	}
	/*
	 * 정수형 5개를  리스트안에 넣고(삽입)
	    정수를 찾는 코드를 구현하기
	    선형리스트로 구현해도 되고
	    이중연결리스트로 구현해도 되고
	    단일연결리스트로 구현해도 되고
	    java.util.List 를 이용해서 구현해도 되고
	    리스트의 크기는? 5
	    정수는? 10
	    정수는? 5
	    정수는? 3
	    정수는? 4
	    정수는? 7
	    검색할 정수는? 5
	    5의 자료는 리스트에 있습니다
	    또는
	    검색할 정수는? 9
	    9의 자료는 리스트에 없습니다
	 * */
}
