import modules.notepad.Notepad;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Notepad!");

        Notepad notepad = new Notepad(
            "line 1\n" +
            "line 2\n" +
            "line 3\n"
        );
        notepad.PrintDebugLine("display");
        notepad.Display();

        notepad.PrintDebugLine("display 1 to 2");
        notepad.Display(1, 2);

        notepad.PrintDebugLine("edit 1st");
        notepad.Insert(1, " Edited 1st!");
        notepad.Display();

        notepad.PrintDebugLine("undo");
        notepad.Undo();
        notepad.Display();

        notepad.PrintDebugLine("redo");
        notepad.Redo();
        notepad.Display();

        notepad.PrintDebugLine("redo again");
        notepad.Redo();
        notepad.Display();

        notepad.PrintDebugLine("delete 1st");
        notepad.Delete(1);
        notepad.Display();

        notepad.PrintDebugLine("undo");
        notepad.Undo();
        notepad.Display();

        notepad.PrintDebugLine("undo again");
        notepad.Undo();
        notepad.Display();

        notepad.PrintDebugLine("delete 1 to 2");
        notepad.Delete(1, 2);
        notepad.Display();

        notepad.PrintDebugLine("undo");
        notepad.Undo();
        notepad.Display();

        notepad.PrintDebugLine("copy 1 to 2 & paste on 3");
        notepad.Copy(1, 2);
        notepad.Paste(3);
        notepad.Display();

        notepad.PrintDebugLine("undo");
        notepad.Undo();
        notepad.Display();

        notepad.PrintDebugLine("redo");
        notepad.Redo();
        notepad.Display();
    }
}
