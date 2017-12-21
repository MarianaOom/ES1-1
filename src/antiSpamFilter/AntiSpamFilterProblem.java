package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;


public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private Anti_Spam_Filter filtro;

	public AntiSpamFilterProblem(Anti_Spam_Filter f) {
		this(f.getRules().size());
		filtro = f;
		// 10 variables (anti-spam filter rules) by default

	}

	/**
	 * @param numberOfVariables The number of variables in study
	 * 
	 */
	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	/**
	 * @see org.uma.jmetal.problem.Problem#evaluate(java.lang.Object)
	 * This method runs the evaluateAutomatic function.
	 */
	public void evaluate(DoubleSolution solution) {
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) // {
			x[i] = solution.getVariableValue(i);
		int[] fx = filtro.evaluateAutomatic(x);
		solution.setObjective(0, fx[0]);
		solution.setObjective(1, fx[1]);

	}
}
