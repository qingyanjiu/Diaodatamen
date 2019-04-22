import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadHugeFile {
	
	private String filePath;
	private int partsNumber;
	private String keyWord;
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public ReadHugeFile(String filePath, int partsNumber) {
		this.filePath = filePath;
		this.partsNumber = partsNumber;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}



	public void read() {
		CountDownLatch finish = new CountDownLatch(partsNumber);
		File file = new File(filePath);
		long size = file.length();
		long partitionSize = size / partsNumber;
		List<HugeFileProcessor> list = new ArrayList();
		for(int i=0;i<partsNumber;i++) {
			long startPosition = partitionSize*i;
			long length = partitionSize;
			if(i == partsNumber-1) {
				length = size - partitionSize * (partsNumber - 1);
			}
			HugeFileProcessor processor = new HugeFileProcessor(filePath,startPosition,length,finish);
			processor.setKeyWord(keyWord);
			list.add(processor);
			executorService.execute(processor);
//			new Thread(processor).start();
		}
		
		try {
			finish.await();
			int total = 0;
			for(HugeFileProcessor p : list) {
				total += p.getKeyWordCount().get();
			}
			System.out.println("Word '"+keyWord+"' appeared : [ "+total+" ] times");
			executorService.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void main(String[] args) {
		String filePath = "/Users/louisliu/Downloads/sg.txt";
//		String filePath = "C:\\Users\\louisliu\\Desktop\\poc.txt";
		int partsNumber = 8;
		ReadHugeFile readHugeFile = new ReadHugeFile(filePath, partsNumber);
		readHugeFile.setKeyWord("诸葛亮");
		readHugeFile.read();
	}

}
