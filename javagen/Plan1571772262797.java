public class Plan1571772262797 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("A");
StartServer("A");

for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}



}

for (int i = 0; i < 2 ; i++) {
DecreaseTraffic("A");
}

StartServer("B");


}
}
