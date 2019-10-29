public class Plan1571768477699 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

DecreaseTraffic("A");

StartServer("B");

}

IncreaseTraffic("B");
StartServer("A");

StartServer("C");
StartServer("B");

StartServer("C");
StartServer("B");

StartServer("A");




}
}
