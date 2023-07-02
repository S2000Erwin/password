public class Main {
    public static void main(String[] args) {
        Genetic g = new Genetic("dfhes56", 100);
        for(int i=0; i<10000; i++) {
            double x = g.evaluate();
            System.out.print(g.get()+"\n");
            if (x == 1.0) {
                System.out.println("Congratulation!!!!!");
                break;
            } else {
                g.evolve();
            }
        }
    }
}
