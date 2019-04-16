import org.ojalgo.optimisation.Expression;
import org.ojalgo.optimisation.ExpressionsBasedModel;
import org.ojalgo.optimisation.Optimisation;
import org.ojalgo.optimisation.Variable;

import java.util.ArrayList;
import java.util.Random;

public class Knapsack {
    public static void main(String[] args) {
        ExpressionsBasedModel model = new ExpressionsBasedModel();
        ArrayList<Variable> vars = new ArrayList<Variable>();

        Random rGen = new Random();

        for(int i=0; i<20; i++) {
            vars.add(
                    Variable
                            .make(String.format("item number %d", i))
                            .lower(0)
                            .upper(100)
                            .weight(rGen.nextInt(5) + rGen.nextDouble())
                            .integer(true)
            );
        }

        ExpressionsBasedModel knapsack = new ExpressionsBasedModel();
        for(Variable v : vars) knapsack.addVariable(v);

        Expression constr = knapsack.addExpression("Weight limit").lower(0).upper(100);
        for(Variable v : vars) constr.set(v, rGen.nextInt(5) + rGen.nextDouble());

        Optimisation.Result solution = knapsack.maximise();

        System.out.println(solution);
        System.out.println(knapsack);
        for(Variable v : vars) {
            System.out.println(v.getName() + " : " + constr.get(v));
        }
    }
}
