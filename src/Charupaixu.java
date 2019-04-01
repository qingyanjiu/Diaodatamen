import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Charupaixu {

	public static void main(String[] args) {
		int[] numbers = {3,2,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		
		for(int i=1;i<numbers.length;i++) {
			int j = i-1;
			for(int h = 0;h<=j;h++) {
				int tmp = numbers[i];
				if(tmp < numbers[h]) {
					numbers[i] = numbers[h];
					numbers[h] = tmp;
				}
			}
		}
		
		for(int k = 0,l = numbers.length;k < l ;k++) {
			System.out.println(numbers[k]);
		}
	}

}
