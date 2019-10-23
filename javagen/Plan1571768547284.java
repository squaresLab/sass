public class Plan1571768547284 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

StartServer("B");
StartServer("C");

DecreaseTraffic("A");

DecreaseTraffic("A");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}


}


DecreaseDimmer("A");



}
}
