public class Plan1525888998294 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("A");
StartServer("C");
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("A");



DecreaseTraffic("A");

}

}
}
