public class Plan1571768936078 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 5 ; i++) {
StartServer("B");
StartServer("C");
StartServer("A");


}

StartServer("A");


}
}
