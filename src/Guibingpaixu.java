
public class Guibingpaixu {
	
	public static void merSort(int[] arr,int left,int right){
        if(left<right){
            int mid = (left+right)/2;
            merSort(arr,left,mid);//��߹鲢����ʹ��������������
            merSort(arr,mid+1,right);//�ұ߹鲢����ʹ��������������
            merge(arr,left,mid,right);//�ϲ�����������
        }
    }
	
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];//ps��Ҳ���Դӿ�ʼ������һ����ԭ�����С��ͬ�����飬��Ϊ�ظ�new�����Ƶ�������ڴ�
        int i = left;
        int j = mid+1;
        int k = 0;
        while(i<=mid&&j<=right){
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while(i<=mid){//�����ʣ��Ԫ������temp��
            temp[k++] = arr[i++];
        }
        while(j<=right){//��������ʣ��Ԫ������temp��
            temp[k++] = arr[j++];
        }
        //��temp�е�Ԫ��ȫ��������ԭ������
        for (int k2 = 0; k2 < temp.length; k2++) {
            arr[k2 + left] = temp[k2];
        }
    }
    public static void main(String args[]){
        int[] test = {3,2,5,7,1,4,6,15,5,2,7,9,10,15,9,17,12};
        merSort(test,0,test.length-1);
        for(int i=0; i<test.length;i++){
            System.out.print(test[i] + " ");
        }
    }

}
