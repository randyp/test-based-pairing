package pairings;

import no.uib.cipr.matrix.DenseMatrix;

import java.util.*;

public class SimulatedAnnealingPairing {

    public static SimulatedAnnealingPairing train(DenseMatrix pairingCosts, Random random, int iterationsPerStudent) {
        List<Integer> initialPairings = new SimulatedAnnealingPairing(pairingCosts, random).pairs;
        return train(pairingCosts, initialPairings, random, iterationsPerStudent);
    }

    public static SimulatedAnnealingPairing train(DenseMatrix pairingCosts,
                                                  List<Integer> initialPairings,
                                                  Random random, int iterationsPerStudent) {
        SimulatedAnnealingPairing bestPairing = new SimulatedAnnealingPairing(pairingCosts, initialPairings);
        System.out.println(String.format("cost0=%.4f", bestPairing.cost));
        System.out.println();


        int startingK = pairingCosts.numRows() * iterationsPerStudent;
        int endingK = 0;
        int numMaxSwaps = pairingCosts.numRows() / 8;

        for (int k = startingK; k >= endingK; k--) {
            double temperature = ((double) k - endingK) / (startingK - endingK);
            int numSwaps = Math.max(1, (int) (numMaxSwaps * temperature));
            SimulatedAnnealingPairing newPairing = bestPairing.randomlySwap(pairingCosts, numSwaps, random);

            if (newPairing.cost < bestPairing.cost) {
                bestPairing = newPairing;
                System.out.println(String.format("New Best Pairing cost=%.4f", bestPairing.cost));
                System.out.println(String.format("nswaps=%d K %d, Temperature %.6f\n", numSwaps, k, temperature));
            }
        }

        return bestPairing;
    }

    private final double cost;
    private final List<Integer> pairs;

    public SimulatedAnnealingPairing(DenseMatrix pairCosts, Random random) {
        pairs = new ArrayList<>();
        for (int personIndex = 0; personIndex < pairCosts.numRows(); personIndex++) {
            pairs.add(personIndex);
        }
        Collections.shuffle(pairs, random);
        this.cost = costOfAllPairings(pairCosts);
    }

    public SimulatedAnnealingPairing(DenseMatrix pairCosts, List<Integer> pairs) {
        this.pairs = pairs;
        this.cost = costOfAllPairings(pairCosts);
    }

    public SimulatedAnnealingPairing randomlySwap(DenseMatrix pairingCosts, int numSwaps, Random random) {
        List<Integer> newPairs = new ArrayList<>(this.pairs);

        for (int i = 0; i < numSwaps; i++) {
            int source = random.nextInt(newPairs.size());
            int target = random.nextInt(newPairs.size());
            if (source != target) {
                int tmp = newPairs.get(source);
                newPairs.set(source, newPairs.get(target));
                newPairs.set(target, tmp);
            }
        }

        return new SimulatedAnnealingPairing(pairingCosts, newPairs);
    }

    public double getCost() {
        return cost;
    }

    public List<List<Integer>> getPairs() {
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < this.pairs.size(); i += 2) {
            int pair0 = this.pairs.get(i);
            if (i + 1< this.pairs.size()) {
                int pair1 = this.pairs.get(i + 1);
                pairs.add(Arrays.asList(pair0, pair1));
            } else {
                pairs.add(Collections.singletonList(pair0));
            }
        }

        for (List<Integer> pair : pairs) {
            Collections.sort(pair);
        }
        Collections.sort(pairs, (integers, integers1) -> integers.get(0) - integers1.get(0));

        return pairs;
    }

    public List<List<Student>> getStudentPairs(List<Student> students) {
        List<List<Student>> studentPairs = new ArrayList<>();
        for (List<Integer> pair : getPairs()) {
            List<Student> studentPair = new ArrayList<>();
            studentPairs.add(studentPair);
            for (Integer studentIndex : pair) {
                studentPair.add(students.get(studentIndex));
            }
        }
        return studentPairs;
    }

    private double costOfAllPairings(DenseMatrix pairCosts) {
        double cost = 0;
        for (int i = 0; i < pairs.size(); i += 2) {
            int pair0 = pairs.get(i);
            if (i + 1 < pairs.size()) {
                int pair1 = pairs.get(i + 1);
                cost += pairCosts.get(pair0, pair1);
            } else {
                cost += pairCosts.get(pair0, pair0);
            }
        }
        return cost;
    }
}
