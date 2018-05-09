public class Plan1525889036891 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

DecreaseDimmer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("C");



}
}
