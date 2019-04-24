import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class HugeFileProcessorWithFuture implements Supplier<Integer> {
	private final long startByte;
	private final long length;
	private final String filePath;
	
	private int keyWordCount = 0;
	
	private String keyWord;
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public int getKeyWordCount() {
		return keyWordCount;
	}

	private static final int BUFFER_SIZE = 1024;

	public HugeFileProcessorWithFuture(String filePath, long startByte, long length) {
		this.startByte = startByte;
		this.length = length;
		this.filePath = filePath;
	}
	
	private int getCountOfWord(String str) {
		return str.split(keyWord).length - 1;
	}
	
	@Override
	public Integer get() {
		RandomAccessFile raf = null;
		try {
			raf=new RandomAccessFile(filePath, "r");
//			System.out.println("�߳�"+Thread.currentThread().getName()+"��ʼ��ȡ�ļ���λ��  "+startByte);
			raf.seek(startByte);
			byte[] buffer = new byte[BUFFER_SIZE];
			int hasRead=0;
			hasRead = raf.read(buffer);
			int i = 0;
			//ÿ���̵߳�����Ҳ�ֶ�����
			//Ĭ����ÿ�ζ�ȡ1024���ֽ�
			while(hasRead > 0 && hasRead < length) {
//				System.out.println("�߳�"+Thread.currentThread().getName()+"  "+new String(buffer,0,hasRead,"utf8"));
				int count = getCountOfWord(new String(buffer,0,hasRead,"utf8"));
				if(keyWord != null)
					keyWordCount += count;
				//���һ���ֿ϶�С��buffer�ĳ���1024
				//�����жϵ�ǰ�Ƿ��Ѿ����˱������ݵ����һ����(length - BUFFER_SIZE*i < BUFFER_SIZE)
				//�������ֻ��Ҫ��ȡ���Ȳ���1024����һ�Σ�Ȼ������ѭ���������������̵߳ģ�������
				if(length - BUFFER_SIZE*i < BUFFER_SIZE) {
					buffer = new byte[(int) (length - BUFFER_SIZE*i)];
					hasRead = raf.read(buffer);
					break;
				} else {
					hasRead = raf.read(buffer);
					i++;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return keyWordCount;
	}
	
	
}
