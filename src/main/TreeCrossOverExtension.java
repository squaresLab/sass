package main;

import org.jgap.InvalidConfigurationException;
import org.jgap.RandomGenerator;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.BranchTypingCross;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

public class TreeCrossOverExtension extends BranchTypingCross {

	public TreeCrossOverExtension(GPConfiguration a_config) {
		super(a_config);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Same as the parent doCross although the mutation operator is changed to
	 * include all possible command gene mutations, including ones which increase
	 * the size of the program. I may implement ones which decrease the size
	 * later.
	 */
	@Override
	protected ProgramChromosome[] doCross(ProgramChromosome a_c0,
			ProgramChromosome a_c1) throws InvalidConfigurationException {
		ProgramChromosome[] c = { a_c0, a_c1 };
		// Choose a point in c1.
		// ---------------------
		int p0;
		RandomGenerator random = getConfiguration().getRandomGenerator();
		if (random.nextFloat() < getConfiguration().getFunctionProb()) {
			// Choose a function.
			// ------------------
			int nf = a_c0.numFunctions();
			if (nf == 0) {
				// No functions there.
				// -------------------
				return c;
			}
			int fctIndex = random.nextInt(nf);
			p0 = a_c0.getFunction(fctIndex);
		} else {
			// Choose a terminal.
			// ------------------
			p0 = a_c0.getTerminal(random.nextInt(a_c0.numTerminals()));
			// Mutate the command's value.
			// ----------------------------
			CommandGene command = a_c0.getNode(p0);
			if (random.nextDouble() <= getConfiguration().getMutationProb()) {
				// This is the section I changed, below in comments is the original
				double val = Math.random();
				if (val < 0.3d) {
					a_c0 = SelfAdaptivePlanMutation.performMutation(getConfiguration(),
							a_c0, p0);
				}
				// section
				/*
				 * if (IMutateable.class.isInstance(command)) { IMutateable term =
				 * (IMutateable) command; command = term.applyMutation(0, 0.3d); if
				 * (command != null) { // Check if mutant's function is allowed. //
				 * -------------------------------------- if (a_c0.getCommandOfClass(0,
				 * command.getClass()) >= 0) { a_c0.setGene(p0, command); } } }
				 */
			}
		}
		// Choose a point in c2 matching the type and subtype of p0.
		// ---------------------------------------------------------
		int p1;
		CommandGene nodeP0 = a_c0.getNode(p0);
		Class type_ = nodeP0.getReturnType();
		int subType = nodeP0.getSubReturnType();
		if (random.nextFloat() < getConfiguration().getFunctionProb()) {
			// Choose a function.
			// ------------------
			int nf = a_c1.numFunctions(type_, subType);
			if (nf == 0) {
				// No functions of that type.
				// --------------------------
				return c;
			}
			p1 = a_c1.getFunction(random.nextInt(nf), type_, subType);
		} else {
			// Choose a terminal.
			// ------------------
			int nt = a_c1.numTerminals(type_, subType);
			if (nt == 0) {
				// No terminals of that type.
				// --------------------------
				return c;
			}
			p1 = a_c1.getTerminal(random.nextInt(a_c1.numTerminals(type_, subType)),
					type_, subType);
			// Mutate the command's value.
			// ----------------------------
			CommandGene command = a_c1.getNode(p1);
			if (random.nextDouble() <= getConfiguration().getMutationProb()) {
				// I replaced the original mututation actions of the class
				double val = Math.random();
				if (val < 0.3d) {
					a_c1 = SelfAdaptivePlanMutation.performMutation(getConfiguration(),
							a_c1, p1);
				}

				/*
				 * if (IMutateable.class.isInstance(command)) { IMutateable term =
				 * (IMutateable) command; command = term.applyMutation(0, 0.3d); if
				 * (command != null) { // Check if mutant's function is allowed. //
				 * -------------------------------------- if (a_c0.getCommandOfClass(0,
				 * command.getClass()) >= 0) { a_c1.setGene(p1, command); } } }
				 */
			}
		}
		/** @todo solve in general */
		// not worrying about below behavior, instead made the mutations occur for
		// functions as well

		/*
		 * if (org.jgap.gp.function.SubProgram.class.isAssignableFrom(a_c1
		 * .getFunctions()[p1].getClass())) { ((IMutateable)
		 * a_c1.getFunctions()[p1]).applyMutation(0, 0.5d); }
		 */
		int s0 = a_c0.getSize(p0); // Number of nodes in c0 from index p0
		int s1 = a_c1.getSize(p1); // Number of nodes in c1 from index p1
		int d0 = a_c0.getDepth(p0); // Depth of c0 from index p0
		int d1 = a_c1.getDepth(p1); // Depth of c1 from index p1
		int c0s = a_c0.getSize(0); // Number of nodes in c0
		int c1s = a_c1.getSize(0); // Number of nodes in c1
		// Check for depth constraint for p1 inserted into c0.
		// ---------------------------------------------------
		if (d0 - 1 + d1/* s1 */> getConfiguration().getMaxCrossoverDepth()
				|| c0s - p0 - s0 < 0
				|| p0 + s1 + c0s - p0 - s0 >= a_c0.getFunctions().length) {
			// Choose the other parent.
			// ------------------------
			c[0] = a_c1;
		} else {
			c[0] = new ProgramChromosome(getConfiguration(),
					a_c0.getFunctions().length, c[0].getFunctionSet(),
					c[0].getArgTypes(), a_c0.getIndividual());
			System.arraycopy(a_c0.getFunctions(), 0, c[0].getFunctions(), 0, p0);
			System.arraycopy(a_c1.getFunctions(), p1, c[0].getFunctions(), p0, s1);
			System.arraycopy(a_c0.getFunctions(), p0 + s0, c[0].getFunctions(), p0
					+ s1, c0s - p0 - s0);
			c[0].redepth();
		}
		// Check for depth constraint for p0 inserted into c1.
		// ---------------------------------------------------
		if (d1 - 1 + d0/* s0 */> getConfiguration().getMaxCrossoverDepth()
				|| c1s - p1 - s1 < 0
				|| p1 + s0 + c1s - p1 - s1 >= a_c1.getFunctions().length) {
			// Choose the other parent.
			// ------------------------
			c[1] = a_c0;
		} else {
			c[1] = new ProgramChromosome(getConfiguration(),
					a_c1.getFunctions().length, c[1].getFunctionSet(),
					c[1].getArgTypes(), a_c1.getIndividual());
			System.arraycopy(a_c1.getFunctions(), 0, c[1].getFunctions(), 0, p1);
			System.arraycopy(a_c0.getFunctions(), p0, c[1].getFunctions(), p1, s0);
			System.arraycopy(a_c1.getFunctions(), p1 + s1, c[1].getFunctions(), p1
					+ s0, c1s - p1 - s1);
			c[1].redepth();
		}
		return c;
	}

}
