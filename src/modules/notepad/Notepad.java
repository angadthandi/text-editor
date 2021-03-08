package modules.notepad;

import java.util.Stack;
import java.util.ArrayList;

public class Notepad {

    private final String newLine = "\n";
    private ArrayList<String> allContent = new ArrayList<String>();
    private ArrayList<String> buffer = new ArrayList<String>();
    private Stack<ArrayList<String>> undoStack = new Stack<ArrayList<String>>();
    private Stack<ArrayList<String>> redoStack = new Stack<ArrayList<String>>();

    public Notepad(String text) {
        String[] splitData = text.split(newLine);
        for (String s: splitData) {
            this.allContent.add(s);
        }
    }

    public void PrintDebugLine(String oper) {
        int total = 40;
        String s = "";
        for (int i=0; i<total; i++) {
            if (total == i * 2) {
                s += oper;
            } else {
                s += "-";
            }
        }
        System.out.println(s);
    }

    public void Display() {
        for (String s : this.allContent) {
            System.out.println(s + newLine);
        }
    }

    public boolean Display(int n, int m) {
        int l = this.allContent.size();

        if (n > l || m > l || n > m) {
            System.out.printf("invalid n: %d or m: %d", n ,m);
        }

        for (int i=n-1; i<m; i++) {
            System.out.println(this.allContent.get(i) + newLine);
        }

        return true;
    }

    public boolean Insert(int n, String text) {
        int l = this.allContent.size();

        if (n > l) {
            System.out.printf("invalid n: %d", n);
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.undoStack.push(copy);
        // copy.clear();

        String tmp = this.allContent.get(n - 1);
        this.allContent.set(n - 1, tmp + text);

        return true;
    }

    public boolean Delete(int n) {
        int l = this.allContent.size();

        if (n > l) {
            System.out.printf("invalid n: %d", n);
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.undoStack.push(copy);
        // copy.clear();

        for (int i=n; i<l-1; i++) {
            this.allContent.add(i, this.allContent.get(i+1));
        }

        return true;
    }

    public boolean Delete(int n, int m) {
        int l = this.allContent.size();

        if (n > l || m > l || n > m) {
            System.out.printf("invalid n: %d or m: %d", n ,m);
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.undoStack.push(copy);
        // copy.clear();

        ArrayList<String> tmp = new ArrayList<String>();
        int c = 0;
        for (int i=0; i<l; i++) {
            if (i >= n || i <= m) {
                continue;
            }

            tmp.add(c, this.allContent.get(i));
            c++;
        }

        this.allContent = tmp;

        return true;
    }

    public boolean Copy(int n, int m) {
        int l = this.allContent.size();

        if (n > l || m > l || n > m) {
            System.out.printf("invalid n: %d or m: %d", n ,m);
        }

        // nullify buffer, before copying...
        this.buffer = new ArrayList<String>();

        for (int i=n-1; i<m; i++) {
            this.buffer.add(this.allContent.get(i));
        }

        return true;
    }

    public boolean Paste(int n) {
        int l = this.allContent.size();
        if (n > l) {
            System.out.printf("invalid n: %d", n);
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.undoStack.push(copy);
        // copy.clear();

        int bl = this.buffer.size();

        int c = n-1;
        for (int i=0; i<bl; i++) {
            if (c < l) {
                this.allContent.add(c, this.buffer.get(i));
            } else {
                this.allContent.add(this.buffer.get(i));
            }

            c++;
        }

        return true;
    }

    public boolean Undo() {
        if (this.undoStack.empty()) {
            System.out.println("nothing to undo!");
            return false;
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.redoStack.push(copy);
        // copy.clear();

        this.allContent = this.undoStack.peek();
        this.undoStack.pop();

        return true;
    }

    public boolean Redo() {
        if (this.redoStack.empty()) {
            System.out.println("nothing to redo!");
            return false;
        }

        ArrayList<String> copy = new ArrayList<String>(this.allContent);
        this.undoStack.push(copy);
        // copy.clear();

        this.allContent = this.redoStack.peek();
        this.redoStack.pop();

        return true;
    }

}
