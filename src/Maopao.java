
public class Maopao {

	public static void main(String[] args) {
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		
		for(int i = 0;i<numbers.length;i++) {
			for(int j = numbers.length-1;j>i;j--) {
				if(numbers[i]>numbers[j]) {
					int tmp = numbers[i];
					numbers[i] = numbers[j];
					numbers[j] = tmp;
				}
			}
		}
		
		for(int k = 0,l = numbers.length;k < l ;k++) {
			System.out.println(numbers[k]);
		}
	}
}
