public class Perceptron {
    float[] weights = new float[2];
    float lr = (float) 0.1;

    public Perceptron() {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (float) (Math.random() * 2 - 1);
        }
    }

    public int guess(float[] inputs) {
        float sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sign(sum);
    }

    public void train(float[] inputs, int target) {
        int guess = guess(inputs);
        int error = target - guess;

        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * lr;
        }
    }

    //activation function
    public int sign(float n) {
        return (n >= 0) ? 1 : -1;
    }
}
