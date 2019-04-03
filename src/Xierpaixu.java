
public class Xierpaixu {

	public static void main(String[] args) {
		int[] numbers = {3,2,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
		int length = numbers.length;
		int l = length;
		while(l>1) {
			l = l/2;
			for(int i=0;i<length;i++) {
				for(int k=i+l;k<length;k=k+l) {
					int tmp = numbers[i];
					if(tmp>numbers[k]) {
						numbers[i]=numbers[k];
						numbers[k]=tmp;
					}
				}
			}
		}

		for(int h=0;h<numbers.length;h++) {
			System.out.println(numbers[h]+" ");
		}
	}

}
