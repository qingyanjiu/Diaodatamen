public class Kuaipai {
	private int oneTimeSort(int start,int end,int[] numbers) {
		while(start < end) {
			while(start < end) {
				if(numbers[start] > numbers[end]) {
					int tmp = numbers[start];
					numbers[start] = numbers[end];
					numbers[end] = tmp;
					break;
				}
				else {
					end = end - 1;
				}
			}
			while(start < end) {
				if(numbers[start] > numbers[end]) {
					int tmp = numbers[start];
					numbers[start] = numbers[end];
					numbers[end] = tmp;
					break;
				}
				else {
					start = start + 1;
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
			this.sort(por + 1, end, numbers);
		}
	}

	
	public static void main(String args[]) {
		Kuaipai kuaipai = new Kuaipai();
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		int size = numbers.length;
		int start = 0;
		int end = size - 1;
		
		kuaipai.sort(start, end, numbers);
		
		
		for(int k = 0,l = numbers.length;k < l ;k++) {
			System.out.println(numbers[k]);
		}
		
	}

}
