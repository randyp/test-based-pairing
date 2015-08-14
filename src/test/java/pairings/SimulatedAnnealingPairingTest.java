package pairings;

import no.uib.cipr.matrix.DenseMatrix;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class SimulatedAnnealingPairingTest {

    @Test
    public void evenNumberOfPeople() throws Exception {
        List<List<Integer>> expectedPairs = Arrays.asList(Arrays.asList(0, 1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7),
                Arrays.asList(8, 9));
        DenseMatrix costMatrix = new DenseMatrix(10, 10);
        for (List<Integer> expectedPair : expectedPairs) {
            Integer i = expectedPair.get(0);
            Integer j = expectedPair.get(1);
            costMatrix.set(i, j, -1);
            costMatrix.set(j, i, -1);
        }
        SimulatedAnnealingPairing pairing = SimulatedAnnealingPairing.train(costMatrix, new Random(15251), 1000);
        List<List<Integer>> actualPairs = pairing.getPairs();
        for (List<Integer> actualPair : actualPairs) {
            Collections.sort(actualPair);
        }
        Collections.sort(actualPairs, (integers, integers1) -> integers.get(0) - integers1.get(0));
        assertEquals(expectedPairs, actualPairs);
    }

    @Test
    public void oddNumberOfPeople() throws Exception {
        //noinspection ArraysAsListWithZeroOrOneArgument
        List<List<Integer>> expectedPairs = Arrays.asList(Arrays.asList(0, 1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7),
                Arrays.asList(8, 9),
                Arrays.asList(10));
        DenseMatrix costMatrix = new DenseMatrix(11, 11);
        for (List<Integer> expectedPair : expectedPairs) {
            if(expectedPair.size() == 2){
                Integer i = expectedPair.get(0);
                Integer j = expectedPair.get(1);
                costMatrix.set(i, j, -1);
                costMatrix.set(j, i, -1);
            }
        }
        SimulatedAnnealingPairing pairing = SimulatedAnnealingPairing.train(costMatrix, new Random(15251), 1000);
        List<List<Integer>> actualPairs = pairing.getPairs();
        for (List<Integer> actualPair : actualPairs) {
            Collections.sort(actualPair);
        }
        Collections.sort(actualPairs, (integers, integers1) -> integers.get(0) - integers1.get(0));
        assertEquals(expectedPairs, actualPairs);
    }

}
