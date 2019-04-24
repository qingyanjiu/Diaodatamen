import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class FutureRunnableTest implements Supplier<Integer> {
	
	private final Integer[] n;
	
	public FutureRunnableTest(Integer[] n) {
		this.n = n;
	}
	
	private Integer getTotal(){
		Integer total = 0;
		for(int j=0;j<n.length;j++) {
			total += n[j];
		}
		return total/0;
	}

	@Override
	public Integer get(){
		Integer total = 0;
		total = this.getTotal();
		return total;
	}
	
	public static void main(String args[]) {
		Integer[] n = {1,2,3,4,5,6,7,8,9,0};
		List<CompletableFuture> l = new ArrayList();
		int i=0;
		while(i<2) {
			Integer[] bs = new Integer[n.length/2];
	        System.arraycopy(n, n.length/2*i, bs, 0, n.length/2);
			CompletableFuture<Integer> f = 
				CompletableFuture.supplyAsync(new FutureRunnableTest(bs))
					.thenApplyAsync((total)->{
					return total + 8;
				}).exceptionally(e->{System.out.println(e.getMessage());return 1;});
			l.add(f);
			i++;
		}
		
		try {
			for(CompletableFuture<Integer> c:l) {
				System.out.println(c.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
