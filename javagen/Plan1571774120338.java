public class Plan1571774120338 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("C");
StartServer("B");

DecreaseTraffic("A");
StartServer("A");
StartServer("B");




}

StartServer("C");

}
}
