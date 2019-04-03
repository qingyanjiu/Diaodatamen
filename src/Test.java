
public class Test {
	
	private int oneTimeSort(int start,int end,int[] numbers) {
		while(start < end) {
			while(start < end) {
				if(numbers[end]<numbers[start]) {
					int tmp = numbers[end];
					numbers[end] = numbers[start];
					numbers[start] = tmp;
					break;
				}
				else {
					end--;
				}
			}
			while(start < end) {
				if(numbers[end]<numbers[start]) {
					int tmp = numbers[end];
					numbers[end] = numbers[start];
					numbers[start] = tmp;
					break;
				}
				else {
					start++;
				}
			}
		}
		return start;
	}
	
	private void sort(int start,int end,int[] numbers) {
		int por = this.oneTimeSort(start, end, numbers);
		if(por > start) {
			this.sort(start, por, numbers);
		}
		if(por < end) {
			this.sort(por+1, end, numbers);
		}
	}
	

	public static void main(String args[]) {
		Test test = new Test();
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		int start = 0;
		int end = numbers.length -1;
		
		test.sort(start, end, numbers);
		
		for(int k=0;k<numbers.length;k++) {
			System.out.println(numbers[k]);
		}
	}
}
