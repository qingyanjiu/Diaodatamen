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
//			System.out.println("线程"+Thread.currentThread().getName()+"开始读取文件的位置  "+startByte);
			raf.seek(startByte);
			byte[] buffer = new byte[BUFFER_SIZE];
			int hasRead=0;
			hasRead = raf.read(buffer);
			int i = 0;
			//每个线程的内容也分段来读
			//默认是每次读取1024个字节
			while(hasRead > 0 && hasRead < length) {
//				System.out.println("线程"+Thread.currentThread().getName()+"  "+new String(buffer,0,hasRead,"utf8"));
				int count = getCountOfWord(new String(buffer,0,hasRead,"utf8"));
				if(keyWord != null)
					keyWordCount += count;
				//最后一部分肯定小于buffer的长度1024
				//所以判断当前是否已经到了本段内容的最后一部分(length - BUFFER_SIZE*i < BUFFER_SIZE)
				//如果是那只需要读取长度不到1024的这一段，然后跳出循环，后面是其他线程的，不读了
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
