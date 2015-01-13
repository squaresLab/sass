package pddl;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import pddl4j.parser.Domain;
import pddl4j.parser.Parser;
import pddl4j.parser.Problem;
import pddl4j.preprocessing.CodedProblem;
import pddl4j.preprocessing.Preprocessing;
import pddl4j.util.MemoryAgent;

//I need to implement this tomorrow
public class ReadPDDL {
	public static void main(String[] args) {
		CodedProblem cp = parseAndEncode();
	}

	public static CodedProblem parseAndEncode() {
		final Parser parser = new Parser();
		String projectDir = Paths.get(".").toAbsolutePath().normalize().toString();
		System.out.println("Current directory: " + projectDir);
		final String domainFilename = projectDir + "/Domain/domain.pddl";
		final String problemFilename = projectDir + "/Problem1/problem.pddl";
		try {
			parser.parse(domainFilename, problemFilename);
		} catch (FileNotFoundException e) {
		}
		if (!parser.getErrorManager().isEmpty()) {
			parser.getErrorManager().printAll();
			System.exit(0);
		}
		final Domain domain = parser.getDomain();
		final Problem problem = parser.getProblem();
		final int traceLevel = 1; // setting to default trace level, can adjust
															// later
		if (traceLevel > 0 && traceLevel != 8) {
			System.out.println();
			System.out.println("Parsing domain file \""
					+ new File(domainFilename).getName() + "\" done successfully");
			System.out.println("Parsing problem file \""
					+ new File(problemFilename).getName() + "\" done successfully\n");
		}
		// if (traceLevel == 8) {
		// Preprocessing.setLogLevel(0);
		// } else {
		Preprocessing.setLogLevel(Math.max(0, traceLevel - 1));
		// }
		long begin = System.currentTimeMillis();
		final CodedProblem pb = Preprocessing.encode(domain, problem);
		long end = System.currentTimeMillis();
		// long preprocessing_time = end - begin;
		// long problem_memory = MemoryAgent.deepSizeOf(pb);
		return pb;
	}

}
