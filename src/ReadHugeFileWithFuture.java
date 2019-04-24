import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadHugeFileWithFuture {
	
	private String filePath;
	private int partsNumber;
	private String keyWord;
	
	private List<CompletableFuture<Integer>> list = new ArrayList();
	
	public List<CompletableFuture<Integer>> getList() {
		return list;
	}


	private static ExecutorService executorService = Executors.newFixedThreadPool(10);
	
	public ReadHugeFileWithFuture(String filePath, int partsNumber, List<CompletableFuture<Integer>> list) {
		this.filePath = filePath;
		this.partsNumber = partsNumber;
		this.list = list;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}



	public void read() {
		File file = new File(filePath);
		long size = file.length();
		long partitionSize = size / partsNumber;
		for(int i=0;i<partsNumber;i++) {
			HugeFileProcessorWithFuture f = new HugeFileProcessorWithFuture(filePath,partitionSize*i,partitionSize);
			f.setKeyWord(keyWord);
			CompletableFuture<Integer> cf = new CompletableFuture().supplyAsync(f);
			list.add(cf);
		}
	}
	

	public static void main(String[] args) {
		String filePath = "/Users/louisliu/Downloads/sg.txt";
		int partsNumber = 8;
		int total = 0;
		List<CompletableFuture<Integer>> list = new ArrayList();
		ReadHugeFileWithFuture readHugeFile = new ReadHugeFileWithFuture(filePath, partsNumber,list);
		readHugeFile.setKeyWord("诸葛亮");
		readHugeFile.read();
		for(CompletableFuture<Integer> c:readHugeFile.getList()) {
			try {
				total += c.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(total);
	}

}
