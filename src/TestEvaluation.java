import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestEvaluation {

	public static void main(String[] args) {

		/**
			 * 
			 */
		// private static final long serialVersionUID = 1L;

		BufferedReader reader = null;
		try {
			/*
			 * Runtime .getRuntime() .exec(
			 * "cd /home/zack/Documents/SoftwareModels/Project/prism-4.2.beta1-linux64/bin"
			 * );
			 */
			System.out.println("Starting to execute prism");
			Process p = Runtime
					.getRuntime()
					.exec(
							"/home/zack/Documents/SoftwareModels/Project/prism-4.2.beta1-linux64/bin/prism /home/zack/Documents/SoftwareModels/Project/firstPrismAttempt.sm /home/zack/Documents/SoftwareModels/Project/firstPrismAttemptProperties.pctl -dtmc -sim");
			System.out.println("issued prism command");
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("prism finished");
			reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1); // being lazy
		}

		// parsing result from command
		String line = null;
		try {
			line = reader.readLine();
			System.out.println("line: " + line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String prismResult = null;
		while (line != null) {
			if (line.contains("Result:")) {
				prismResult = line.substring("Result:".length());
				prismResult = prismResult.trim();
				break;
			}
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("line: " + line);
		}
		if (prismResult == null) {
			new Exception().printStackTrace();
			System.exit(1); // being lazy again
		} else {
			System.out.println("Result is: " + Double.parseDouble(prismResult));
		}
		// return new Double(0); // shouldn't be reachable but needed for compiler

	}
}
