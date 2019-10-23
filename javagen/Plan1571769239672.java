public class Plan1571769239672 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("C");
}

StartServer("A");
DecreaseTraffic("A");

DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
}





}
}
