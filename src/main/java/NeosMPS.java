import org.ojalgo.optimisation.MathProgSysModel;
import org.ojalgo.optimisation.Optimisation;

import java.io.File;
import java.nio.file.Paths;

public class NeosMPS {
    public static void main(String[] args) {
        File mpsInput = new File(Paths.get("data", "neos-4338804-snowy.mps").toString());
        System.out.printf("Input file: %s\n", Paths.get("data", "neos-4338804-snowy.mps").toString());
        MathProgSysModel neos = MathProgSysModel.make(mpsInput);

        if(neos.validate()) System.out.println("Model is OK!");
        else throw new RuntimeException("Model problem!!!");

        Optimisation.Result neosResult = neos.solve();
        System.out.println(neosResult);
    }
}
