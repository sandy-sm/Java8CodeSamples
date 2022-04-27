package sm.sandy.java8;

import java.util.function.Function;
import java.util.function.Supplier;

public class Function1 {

    public static void main(String[] args) {
        class Cycle {
            int id;
            String name;
            String type;

            Cycle(int id, String name, String type) {
                this.id = id;
                this.name = name;
                this.type = type;
            }

            public Cycle() {

            }

            public Cycle(Cycle c){
                this.id = c.id;
                this.name = c.name;
                this.type = c.type;
            }
        }

        class CycleInfo {
            int id;
            String name;
            String typeOfCycle;

            @Override
            public String toString() {
                return "CycleInfo{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", typeOfCycle='" + typeOfCycle + '\'' +
                        '}';
            }
        }

        Function<Cycle, CycleInfo> cycleToCycleInfo = (c) -> {
            CycleInfo cycleInfo = new CycleInfo();
            cycleInfo.id = c.id;
            cycleInfo.name = c.name;
            cycleInfo.typeOfCycle = c.type;
            return cycleInfo;
        };

        Supplier<Cycle> c1 = Cycle::new;
        Cycle c = c1.get();

        Function<Cycle, Cycle> f = c2 -> new Cycle(c2);
        Cycle d = f.apply(c);


        CycleInfo ci = cycleToCycleInfo.apply(new Cycle(1, "polygon cascade 2", "mtb"));

        System.out.println(ci);
    }


}
