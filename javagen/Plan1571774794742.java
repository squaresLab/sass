public class Plan1571774794742 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


StartServer("B");
StartServer("C");

DecreaseTraffic("A");

StartServer("A");


}

StartServer("C");


}
}
