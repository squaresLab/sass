public class Plan1571773736858 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}

for (int i = 0; i < 3 ; i++) {
StartServer("C");
StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
StartServer("A");
StartServer("C");

StartServer("B");

}


StartServer("B");
StartServer("A");



}
}
