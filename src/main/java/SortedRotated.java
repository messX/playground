class SortedRotated{
    public static boolean binarySearch(int l, int r, int[] input, int search){
        boolean res = false;
        if(l > r){
            return res;
        }
        int avg = (l+r)/2;
        if(search == input[avg]){
            return true;
        }
        else{
            if(search < input[avg]){
                return binarySearch(l, avg-1, input, search);
            }
            else{
                return binarySearch(avg+1, r, input, search);
            }
        }
    }
    public static boolean rotatedSearch(int l, int r, int search, int[] input){
        boolean res = false;
        if(l > r){
            return res;
        }
        int avg = (l + r)/2;
        if(input[avg] == search){
            return true;
        }
        else{
            if(input[avg] < input[l]){
                return rotatedSearch(l, avg-1, search, input);
            }
            else if(input[avg] > input[r]){
                return rotatedSearch(avg + 1, r, search, input);
            }
            if(input[avg] > search){
                return binarySearch(l, avg-1, input, search);
            }
            else{
                return binarySearch(avg+1, r, input, search);
            }
        }
    }
    public static void main(String[] args) {
        int[] input = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(rotatedSearch(8, input.length-1, 0, input));
    }
}