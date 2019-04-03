public class Duipaixu {
	
	private void sort(int length,int[] numbers) {
		for(int cIndex = length/2 - 1;cIndex>=0;cIndex--) {
			this.adjustHeap(cIndex, length, numbers);
		}
		for(int k = numbers.length - 1;k>0;k--) {
			int tmp = numbers[k];
			numbers[k] = numbers[0];
			numbers[0] = tmp;
			this.adjustHeap(0, k, numbers);
		}
	}
	
	private void adjustHeap(int i,int length,int[] numbers) {
		for(int j=i*2 + 1;j<length;j=j*2+1) {
			if(j+1<length && numbers[j] < numbers[j+1]) {
				j = j+1;
			}
			if(numbers[j] > numbers[i]) {
				int tmp = numbers[j];
				numbers[j] = numbers[i];
				numbers[i] = tmp;
				i=j;
			}
			else {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Duipaixu paixu = new Duipaixu();
		int[] numbers = {2,3,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		paixu.sort(numbers.length, numbers);
		
		for(int i=0; i<numbers.length;i++){
            System.out.print(numbers[i] + " ");
        }
	}

}
