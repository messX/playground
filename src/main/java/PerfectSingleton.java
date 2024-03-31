public class PerfectSingleton {
    public static volatile PerfectSingleton perfectSingleton;
    private PerfectSingleton(){}
    public static PerfectSingleton getSingleTon(){
        if(perfectSingleton == null){
            synchronized (PerfectSingleton.class){
                if(perfectSingleton == null)
                    perfectSingleton = new PerfectSingleton();
            }
        }
        return perfectSingleton;
    }
}
