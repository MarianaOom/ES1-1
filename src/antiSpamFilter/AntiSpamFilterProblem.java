package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;


public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	int abc = 0;
	private Filtro_Anti_Spam filtro;

	public AntiSpamFilterProblem(Filtro_Anti_Spam f) {
		this(335);
		filtro = f;
		// 10 variables (anti-spam filter rules) by default

	}

	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			System.out.println(i);
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}
		System.out.println(lowerLimit);
		System.out.println(upperLimit);
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void evaluate(DoubleSolution solution) {
		double[] x = new double[getNumberOfVariables()];
		for (int i = 0; i < solution.getNumberOfVariables(); i++) // {
			x[i] = solution.getVariableValue(i);
		if (solution.getLowerBound(0) == solution.getUpperBound(0))
			System.out.println(
					"dfgchvjklçºç~lçkljhkihbjnklkkm,m,,m,m,mm,m,m,,,m,m,m,m,m,m,m,m,m,m,m,m,m,m,m,m,n,n,n,n,n,n,n,n,n,,b,b,b,b,b,b,v,v,vcv,c,c,c,c,jkbhjkbjhknbkgjfhcgxfdzxdgchvjbknhlkjçlmkºçlº");
		int[] fx = filtro.evaluateAutomatic(x);
		solution.setObjective(0, fx[0]);
		solution.setObjective(1, fx[1]);

	}
}
