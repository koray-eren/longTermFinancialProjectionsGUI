package sample;

public class AssumptionInput {
    private double indexation;
    private int projectionLength;

    public AssumptionInput(double indexation, int projectionLength) {
        this.indexation = indexation;
        this.projectionLength = projectionLength;
    }

    public double getIndexation() {
        return indexation;
    }

    public void setIndexation(double indexation) {
        this.indexation = indexation;
    }

    public int getProjectionLength() {
        return projectionLength;
    }

    public void setProjectionLength(int projectionLength) {
        this.projectionLength = projectionLength;
    }
}
