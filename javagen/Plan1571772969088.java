public class Plan1571772969088 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

StartServer("B");
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}



StartServer("A");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



IncreaseTraffic("B");

for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("B");
StartServer("C");

StartServer("A");



}
}
