import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		int n = 10;
		float denom = n;
		for(int i = 0; i < n; i++) {
			float num = i+1;
			int per = (int) ((num/denom)*100);
			System.out.println();
		}
	}

}
