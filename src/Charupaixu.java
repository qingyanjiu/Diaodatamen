import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Charupaixu {

	public static void main(String[] args) {
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		List<Integer> result = new ArrayList();
		result.add(numbers[0]);
		for(int i=1;i<numbers.length;i++) {
			result.add(numbers[i]);
			int len = result.size();
			for(int j=0;j<len;j++){
				int last = result.get(len-1);
				if(last < result.get(j)) {
					Collections.swap(result, j, len-1);
				}
			}
		}
		for(int k = 0,l = result.size();k < l ;k++) {
			System.out.println(result.get(k));
		}
	}

}
