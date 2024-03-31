
public class Trie {
    Trie[] alphabetList;
    Boolean isLast;

    public Trie[] getAlphabetList() {
        return alphabetList;
    }

    public void setAlphabetList(Trie[] alphabetList) {
        this.alphabetList = alphabetList;
    }

    public Boolean getLast() {
        return isLast;
    }

    public void setLast(Boolean last) {
        isLast = last;
    }

    public Trie(Boolean isLast) {
        this.alphabetList = new Trie[26];
        this.isLast = isLast;
    }

    public Trie(){
        this.alphabetList = new Trie[26];
    }

    private int getIndex(char a){
        return a - 'a';
    }

    public void insert(String str){
        Trie trie = this;
        for (int i = 0; i < str.toCharArray().length; i++) {
            char chr = str.toCharArray()[i];
            if( trie.getAlphabetList()[(getIndex(chr))] == null){
                Trie node = new Trie(i == str.toCharArray().length -1);
                trie.getAlphabetList()[getIndex(chr)] = node;
            }
            trie = trie.getAlphabetList()[(getIndex(chr))];
        }
    }

    public boolean search(String str){
        Trie trie = this;
        for (int i = 0; i < str.toCharArray().length; i++) {
            char chr = str.toCharArray()[i];
            if(trie == null || trie.getAlphabetList()[getIndex(chr)] == null){
                return false;
            }
            trie = trie.getAlphabetList()[getIndex(chr)];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie test = new Trie();
        test.insert("test");
        System.out.println(test.search("test"));
        System.out.println(test.search("testqw"));
    }
}
