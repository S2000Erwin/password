import java.util.Random;

public class Genetic {
    Random rng = new Random();
    DNA [] population;
    String target;
    double [] scores;
    double maxScore = 0.0;
    public Genetic(String target, int size) {
        this.target = target;
        // 0-31
        // 32 space 33 !
        // ASCII
        // 0 1 2 3 4 5 6 7 8 9 A B C D E F
        // 10 11 12 13 14 15 16 17 18 19 1A 1B 1C 1D 1E 1F
        // Hexadecimal 0x20 space 0x21 !
        // Decimal 32 space 33 !
        // Hexadecimal 0x30 '0' 0x31 '1'.. 0x39 '9'
        // 0x41 'A' ... 0x49 'I' 0x4A 'J' ...
        // 0x61 'a' ... 0x69 'i' 0x6A 'j' ....
        // 0x20 ' ' ... 0x7E
        // 0x7E <--> 126
        // 100 x 100 x 100 x 100 x....x 100 = 1 e20
        // 1 e20 / 1 e6 = 1 e14 seconds
        // 1 e14 / 86400 = 1 e9 days
        // 1 e9 / 365 = 3 e6 years
        population = new DNA[size];
        scores = new double[size];
        for(int i=0; i<size; i++) {
            population[i] = new DNA(target.length());
            scores[i] = 0.0;
        }
    }
    public void evolve() {
        DNA [] newPop = new DNA[population.length];
        for(int i=0; i< population.length; i++) {
            //population[i].random();
            DNA father = population[pick()];
            DNA mother = population[pick()];
            DNA child = father.crossover(mother);
            child.mutate(0.02);
            newPop[i] = child;
        }
        population = newPop;
    }

    private int pick() {
        double total = 0.0;
        for(int i=0; i< population.length; i++) {
            total += scores[i];
        }
        double index = rng.nextFloat() * total;
        double acc = 0.0;
        int result = 0;
        for(int i=0; i< population.length; i++) {
            acc += scores[i];
            if ( acc >= index ) {
                result = i;
                break;
            }
        }
        return result;
    }

    public double evaluate() {
        maxScore = 0.0;
        for(int i=0; i< population.length; i++) {
            int score = 0;
            for(int k=0; k<target.length(); k++)
                if (population[i].get(k)==target.charAt(k))
                    score++;
            scores[i] = (double)score / target.length();
            if (scores[i] > maxScore) maxScore = scores[i];
        }
        return maxScore;
    }
    public String get() {
        for(int i=0; i< population.length; i++) {
            if (scores[i] == maxScore) {
                return population[i].get();
            }
        }
        return "";
    }
}
