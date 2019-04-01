
public class Xuanzepaixu {
	
	private void sort(int[] numbers) {
		for(int k=0;k<numbers.length;k++) {
			int index = k;
			int min = index;
			for(int i=index,len=numbers.length;i<len;i++) {
				if(numbers[min]>numbers[i]) {
					min = i;
				}
			}
			int tmp = numbers[index];
			numbers[index] = numbers[min];
			numbers[min] = tmp;
		}
	}

	public static void main(String[] args) {
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		Xuanzepaixu xuanze = new Xuanzepaixu();
		xuanze.sort(numbers);
		
		for(int k = 0,l = numbers.length;k < l ;k++) {
			System.out.println(numbers[k]);
		}
	}

}
