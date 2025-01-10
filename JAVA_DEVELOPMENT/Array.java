public class Array {
    public static void main(String[] args) {
        int[] oneDArray = {1, 2, 3, 4, 5};
        for (int i = 0; i < oneDArray.length; i++) {
            oneDArray[i] += 1;
        }
        for(int i=0;i<oneDArray.length;i++){
            System.out.print(oneDArray[i] + " ");
        }
        System.out.println();
        int[][] twoDArray = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        for(int i = 0; i < twoDArray.length; i++) {
            for (int j = 0; j < twoDArray[i].length; j++) {
                twoDArray[i][j] += 1;
            }
        }
        for(int i=0;i<twoDArray.length;i++){
            for(int j=0;j<twoDArray[0].length;j++){
                System.out.print(twoDArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}