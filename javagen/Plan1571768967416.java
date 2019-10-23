public class Plan1571768967416 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
IncreaseDimmer("A");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
StartServer("A");
StartServer("C");

StartServer("C");


DecreaseTraffic("A");

}


StartServer("A");

}
}
