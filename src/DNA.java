import java.util.Random;

public class DNA {
    Random rng = new Random();
    char [] gene;
    public DNA(int length) {
        gene = new char[length];
        random();
        //System.out.println(gene);
    }
    public char get(int k) {
        return gene[k];
    }
    public String get() {
        String str = new String(gene);
        return str;
    }
    public void random() {
        for (int i=0; i<gene.length; i++) {
            gene[i] = (char)(rng.nextInt(0x5f) + 0x20);
        }
    }
    public DNA crossover(DNA mother) {
        DNA child = new DNA(this.gene.length);
        for (int i=0; i<gene.length; i++) {
            if ((i%2)==0) {
                child.gene[i] = this.gene[i];
            } else {
                child.gene[i] = mother.gene[i];
            }
        }
        return child;
    }
    public void mutate(double chance) {
        for (int i=0; i<gene.length; i++) {
            double dice = rng.nextDouble();
            if (dice <= chance) {
                gene[i] = (char)(rng.nextInt(0x5f) + 0x20);
            }
        }
    }
}
